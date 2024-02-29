package com.enviro.assessment.grad001.boitumelotshehla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NoticeExportRequestDto {
    private Long productId;
    private LocalDate startDate;
    private LocalDate endDate;

}
