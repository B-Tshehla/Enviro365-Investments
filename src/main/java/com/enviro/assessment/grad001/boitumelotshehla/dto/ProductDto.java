package com.enviro.assessment.grad001.boitumelotshehla.dto;

import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private Long productId;
    private Long investorId;
    private String type;
    private String name;
    private BigDecimal currentBalance;
}
