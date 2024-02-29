package com.enviro.assessment.grad001.boitumelotshehla.service.impl;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.NoticeExportRequestDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.WithdrawalNoticeDto;
import com.enviro.assessment.grad001.boitumelotshehla.mapper.InvestorMapper;
import com.enviro.assessment.grad001.boitumelotshehla.mapper.ProductMapper;
import com.enviro.assessment.grad001.boitumelotshehla.mapper.WithdrawalNoticeMapper;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.model.WithdrawalNotice;
import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import com.enviro.assessment.grad001.boitumelotshehla.repository.WithdrawalNoticeRepository;
import com.enviro.assessment.grad001.boitumelotshehla.service.InvestorService;
import com.enviro.assessment.grad001.boitumelotshehla.service.NotificationService;
import com.enviro.assessment.grad001.boitumelotshehla.service.ProductService;
import com.enviro.assessment.grad001.boitumelotshehla.service.WithdrawalNoticeService;
import com.enviro.assessment.grad001.boitumelotshehla.service.specification.WithdrawalNoticeSpecification;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WithdrawalNoticeServiceImpl implements WithdrawalNoticeService {
    public static BigDecimal WITHDRAWAL_PERCENTAGE = BigDecimal.valueOf(0.90);
    private final InvestorService investorService;
    private final InvestorMapper investorMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final WithdrawalNoticeMapper withdrawalNoticeMapper;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public void createWithdrawalNotice(WithdrawalNoticeDto withdrawalNoticeDto) {
        log.debug("createWithdrawalNotice() - start: withdrawalNoticeDto = {}", withdrawalNoticeDto);
        ProductDto productDto = productService.findProductById(withdrawalNoticeDto.getProductId());
        validateWithdrawal(productDto, withdrawalNoticeDto);
        BigDecimal closingBalance = productDto.getCurrentBalance().subtract(withdrawalNoticeDto.getWithdrawal());

        InvestorDto investorDto = investorService.findInvestorById(productDto.getInvestorId());
        Investor investor = investorMapper.toEntity(investorDto);
        Product product = productMapper.toEntity(productDto, investor);
        WithdrawalNotice withdrawalNotice = withdrawalNoticeMapper.toEntity(withdrawalNoticeDto, product);
        withdrawalNoticeRepository.save(withdrawalNotice);

        updateProduct(closingBalance, productDto.getProductId());
        notificationService.sendWithdrawalNotification(investorDto.getFirstName(),
                investorDto.getEmail(),
                withdrawalNoticeDto.getWithdrawal(),
                productDto.getCurrentBalance(),
                closingBalance);
        log.debug("createWithdrawalNotice() - end:");
    }

    @Override
    public void exportNoticesToCsv(NoticeExportRequestDto noticeExportRequestDto, HttpServletResponse response) {
        log.debug("exportNoticesToCsv() - start: noticeExportRequestDto = {}", noticeExportRequestDto);
        Specification<WithdrawalNotice> specification = WithdrawalNoticeSpecification.getNoticesByProductAndDateRange(noticeExportRequestDto);
        List<WithdrawalNotice> withdrawalNoticeList = withdrawalNoticeRepository.findAll(specification);
        writeToCsv(withdrawalNoticeList, response);
        log.debug("exportNoticesToCsv() - end:");
    }

    private void validateWithdrawal(ProductDto productDto, WithdrawalNoticeDto withdrawalNoticeDto) {
        Integer investorAge = investorService.getInvestorAge(productDto.getInvestorId());
        BigDecimal availableAmount = productDto.getCurrentBalance().multiply(WITHDRAWAL_PERCENTAGE);

        if (investorAge < 65 && productDto.getType().equals(ProductType.RETIREMENT)) {
            throw new ValidationException("Withdrawal failed: You are younger than 65 and want to withdraw from retirement");
        }
        if (withdrawalNoticeDto.getWithdrawal().compareTo(productDto.getCurrentBalance()) > 0) {
            throw new ValidationException("Withdrawal failed: The withdrawal amount is greater than current amount");
        }
        if (withdrawalNoticeDto.getWithdrawal().compareTo(availableAmount) > 0) {
            throw new ValidationException("Withdrawal failed: The withdrawal amount is greater than the available amount");
        }
    }

    private void updateProduct(BigDecimal closingBalance, Long productId) {
        ProductDto productDto = new ProductDto().setCurrentBalance(closingBalance);
        productService.updateProductById(productDto, productId);
    }

    private void writeToCsv(List<WithdrawalNotice> notices, HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=notices.csv");
            CSVWriter writer = csvWriter(notices, response);
            writer.close();
        } catch (IOException e) {
            log.error("Error writing CSV file: {}", e.getMessage());
        }
    }

    private static CSVWriter csvWriter(List<WithdrawalNotice> notices, HttpServletResponse response) throws IOException {
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));

        String[] header = {"Withdrawal ID", "Withdrawal Date", "Amount", "Product Type", "Product Name", "Investor Name",
                "Bank Name", "Account Type", "Branch Code", "Account Number", "Account Holder"};
        writer.writeNext(header);
        notices.stream()
                .map(notice -> new String[]{
                        String.valueOf(notice.getId()),
                        notice.getWithdrawalDate().toString(),
                        notice.getAmount().toString(),
                        notice.getProduct().getType().name(),
                        notice.getProduct().getName(),
                        String.format("%s %s",notice.getProduct().getInvestor().getFirstName(),notice.getProduct().getInvestor().getLastName()),
                        notice.getBankName(),
                        notice.getAccountType(),
                        notice.getBranchCode(),
                        notice.getAccountNumber(),
                        notice.getAccountHolder()
                })
                .forEach(writer::writeNext);
        return writer;
    }
}


