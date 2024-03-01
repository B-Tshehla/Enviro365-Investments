package com.enviro.assessment.grad001.boitumelotshehla.service;


import com.enviro.assessment.grad001.boitumelotshehla.dto.NoticeExportRequestDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.WithdrawalNoticeDto;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The WithdrawalNoticeService interface provides methods for managing withdrawal notices.
 * This includes creating new withdrawal notices and exporting them to CSV format.
 */
public interface WithdrawalNoticeService {

    /**
     * Creates a new withdrawal notice based on the provided WithdrawalNoticeDto.
     *
     * @param withdrawalNoticeDto The data transfer object containing information for the new withdrawal notice.
     */
    void createWithdrawalNotice(WithdrawalNoticeDto withdrawalNoticeDto);

    /**
     * Exports withdrawal notices to a CSV file based on the provided export request parameters.
     *
     * @param noticeExportRequestDto The data transfer object containing parameters for the export request.
     * @param response               The HttpServletResponse object to handle the response.
     */
    void exportNoticesToCsv(NoticeExportRequestDto noticeExportRequestDto, HttpServletResponse response);

}