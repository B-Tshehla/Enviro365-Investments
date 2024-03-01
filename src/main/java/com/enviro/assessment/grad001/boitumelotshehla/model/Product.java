package com.enviro.assessment.grad001.boitumelotshehla.model;

import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Data
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private String name;
    private BigDecimal currentBalance;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "investor_id")
    Investor investor;
}
