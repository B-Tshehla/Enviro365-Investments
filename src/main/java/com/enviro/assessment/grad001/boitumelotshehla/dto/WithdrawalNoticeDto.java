package com.enviro.assessment.grad001.boitumelotshehla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WithdrawalNoticeDto {
    private Long productId;
    private BigDecimal withdrawal;
    private String bankName;
    private String accountType;
    private String branchCode;
    private String accountNumber;
    private String accountHolder;
}
