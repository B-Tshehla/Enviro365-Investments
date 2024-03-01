package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.AbstractIntegrationTest;
import com.enviro.assessment.grad001.boitumelotshehla.dto.NoticeExportRequestDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.WithdrawalNoticeDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.model.WithdrawalNotice;
import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import com.enviro.assessment.grad001.boitumelotshehla.repository.ProductRepository;
import com.enviro.assessment.grad001.boitumelotshehla.repository.WithdrawalNoticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class WithdrawalNoticeControllerTest extends AbstractIntegrationTest {

    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WithdrawalNoticeRepository withdrawalNoticeRepository;

    @Test
    void createWithdrawalNoticeTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Investor investor = investorRepository.save(new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("5002215307084"));
        Product product = productRepository.save(new Product()
                .setInvestor(investor)
                .setName("Retirement Fund")
                .setType(ProductType.RETIREMENT)
                .setCurrentBalance(BigDecimal.valueOf(50000.00)));

        assertNotNull(product);
        WithdrawalNoticeDto withdrawalNoticeDto = new WithdrawalNoticeDto()
                .setProductId(product.getId())
                .setWithdrawal(BigDecimal.valueOf(1000.00))
                .setBankName("Example Bank")
                .setAccountHolder("Test Name")
                .setAccountType("Savings")
                .setAccountNumber("12345678")
                .setBranchCode("47224");
        BigDecimal closingBalance = product.getCurrentBalance().subtract(withdrawalNoticeDto.getWithdrawal()).setScale(2, RoundingMode.HALF_UP);
        webTestClient.post()
                .uri("api/v1/withdrawal")
                .bodyValue(withdrawalNoticeDto)
                .exchange()
                .expectStatus()
                .isOk();

        Product updatedProduct = productRepository.findById(product.getId()).orElse(null);
        assertNotNull(updatedProduct);
        assertEquals(updatedProduct.getCurrentBalance(), closingBalance);
    }


    @Test
    void createWithdrawalNoticeByInvalidProductTest() {
        Long productId = 99L;

        WithdrawalNoticeDto withdrawalNoticeDto = new WithdrawalNoticeDto()
                .setProductId(productId)
                .setWithdrawal(BigDecimal.valueOf(1000.00))
                .setBankName("Example Bank")
                .setAccountHolder("Test Name")
                .setAccountType("Savings")
                .setAccountNumber("12345678")
                .setBranchCode("47224");

        webTestClient.post()
                .uri("api/v1/withdrawal")
                .bodyValue(withdrawalNoticeDto)
                .exchange()
                .expectStatus()
                .isBadRequest();

        List<WithdrawalNotice> withdrawalNotices = withdrawalNoticeRepository.findAll();
        assertNotNull(withdrawalNotices);
        assertEquals(withdrawalNotices.size(), 0);
    }

    @Test
    void validateRetirementWithdrawalTest() {
        Investor investor = investorRepository.save(new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("8802215307084"));
        Product product = productRepository.save(new Product()
                .setInvestor(investor)
                .setName("Retirement Fund")
                .setType(ProductType.RETIREMENT)
                .setCurrentBalance(BigDecimal.valueOf(50000.00)));

        System.out.println(productRepository.findAll());
        WithdrawalNoticeDto withdrawalNoticeDto = new WithdrawalNoticeDto()
                .setProductId(product.getId())
                .setWithdrawal(BigDecimal.valueOf(1000.00))
                .setBankName("Example Bank")
                .setAccountHolder("Test Name")
                .setAccountType("Savings")
                .setAccountNumber("12345678")
                .setBranchCode("47224");

        webTestClient.post()
                .uri("api/v1/withdrawal")
                .bodyValue(withdrawalNoticeDto)
                .exchange()
                .expectStatus()
                .isBadRequest();

        List<WithdrawalNotice> withdrawalNotices = withdrawalNoticeRepository.findAll();
        assertNotNull(withdrawalNotices);
        assertEquals(withdrawalNotices.size(), 0);
    }

    @Test
    void validateWithdrawalAmountGreaterThanCurrentAmountTest() {
        Investor investor = investorRepository.save(new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("5002215307084"));
        Product product = productRepository.save(new Product()
                .setInvestor(investor)
                .setName("Retirement Fund")
                .setType(ProductType.RETIREMENT)
                .setCurrentBalance(BigDecimal.valueOf(50000.00)));

        WithdrawalNoticeDto withdrawalNoticeDto = new WithdrawalNoticeDto()
                .setProductId(product.getId())
                .setWithdrawal(BigDecimal.valueOf(60000.00))
                .setBankName("Example Bank")
                .setAccountHolder("Test Name")
                .setAccountType("Savings")
                .setAccountNumber("12345678")
                .setBranchCode("47224");

        webTestClient.post()
                .uri("api/v1/withdrawal")
                .bodyValue(withdrawalNoticeDto)
                .exchange()
                .expectStatus()
                .isBadRequest();

        List<WithdrawalNotice> withdrawalNotices = withdrawalNoticeRepository.findAll();
        assertNotNull(withdrawalNotices);
        assertEquals(withdrawalNotices.size(), 0);
    }

    @Test
    void validateWithdrawalAmountMoreThanAvailableAmountTest() {
        Investor investor = investorRepository.save(new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("5002215307084"));
        Product product = productRepository.save(new Product()
                .setInvestor(investor)
                .setName("Retirement Fund")
                .setType(ProductType.RETIREMENT)
                .setCurrentBalance(BigDecimal.valueOf(50000.00)));

        WithdrawalNoticeDto withdrawalNoticeDto = new WithdrawalNoticeDto()
                .setProductId(product.getId())
                .setWithdrawal(BigDecimal.valueOf(46000.00))
                .setBankName("Example Bank")
                .setAccountHolder("Test Name")
                .setAccountType("Savings")
                .setAccountNumber("12345678")
                .setBranchCode("47224");

        webTestClient.post()
                .uri("api/v1/withdrawal")
                .bodyValue(withdrawalNoticeDto)
                .exchange()
                .expectStatus()
                .isBadRequest();

        List<WithdrawalNotice> withdrawalNotices = withdrawalNoticeRepository.findAll();
        assertNotNull(withdrawalNotices);
        assertEquals(withdrawalNotices.size(), 0);
    }

    @Test
    void exportNoticesToCsvTest(){
        Investor investor = investorRepository.save(new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("5002215307084"));
        Product product = productRepository.save(new Product()
                .setInvestor(investor)
                .setName("Retirement Fund")
                .setType(ProductType.RETIREMENT)
                .setCurrentBalance(BigDecimal.valueOf(50000.00)));
        WithdrawalNoticeDto withdrawalNoticeDto = new WithdrawalNoticeDto()
                .setProductId(product.getId())
                .setWithdrawal(BigDecimal.valueOf(1000.00))
                .setBankName("Example Bank")
                .setAccountHolder("Test Name")
                .setAccountType("Savings")
                .setAccountNumber("12345678")
                .setBranchCode("47224");

        NoticeExportRequestDto noticeExportRequestDto = new NoticeExportRequestDto()
                .setProductId(product.getId())
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now());


        webTestClient.post()
                .uri("api/v1/withdrawal")
                .bodyValue(withdrawalNoticeDto)
                .exchange()
                .expectStatus()
                .isOk();

        byte[] result = webTestClient.post()
                .uri("/api/v1/export")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(noticeExportRequestDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(byte[].class)
                .returnResult()
                .getResponseBody();

        assertNotNull(result);
        assertNotEquals(result.length, 0);

    }
}