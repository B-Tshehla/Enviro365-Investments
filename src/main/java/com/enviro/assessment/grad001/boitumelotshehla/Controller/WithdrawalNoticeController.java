package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.dto.NoticeExportRequestDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.WithdrawalNoticeDto;
import com.enviro.assessment.grad001.boitumelotshehla.service.WithdrawalNoticeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class WithdrawalNoticeController {

    private final WithdrawalNoticeService withdrawalNoticeService;
    @PostMapping("/withdrawal")
    void createWithdrawalNotice(@RequestBody WithdrawalNoticeDto withdrawalNoticeDto){
        log.debug("createWithdrawalNotice() - start: withdrawalNoticeDto = {}",withdrawalNoticeDto);
        withdrawalNoticeService.createWithdrawalNotice(withdrawalNoticeDto);
        log.debug("createWithdrawalNotice() - end:");
    }

    @PostMapping("/export")
    void exportNoticesToCsv(@RequestBody NoticeExportRequestDto noticeExportRequestDto, HttpServletResponse response){
        log.debug("exportNoticesToCsv() - start: noticeExportRequestDto = {}", noticeExportRequestDto);
        withdrawalNoticeService.exportNoticesToCsv(noticeExportRequestDto, response);
        log.debug("exportNoticesToCsv() - end:");
    }
}
