package com.enviro.assessment.grad001.boitumelotshehla.model;

import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "product_seq")
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private String name;
    private BigDecimal currentBalance;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "investor_id")
    Investor investor;
}
