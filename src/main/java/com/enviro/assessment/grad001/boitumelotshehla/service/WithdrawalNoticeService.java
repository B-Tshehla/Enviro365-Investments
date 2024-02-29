package com.enviro.assessment.grad001.boitumelotshehla.service;


import com.enviro.assessment.grad001.boitumelotshehla.dto.NoticeExportRequestDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.WithdrawalNoticeDto;
import jakarta.servlet.http.HttpServletResponse;

public interface WithdrawalNoticeService {

    void createWithdrawalNotice(WithdrawalNoticeDto withdrawalNoticeDto);
    void exportNoticesToCsv(NoticeExportRequestDto noticeExportRequestDto, HttpServletResponse response);

}
