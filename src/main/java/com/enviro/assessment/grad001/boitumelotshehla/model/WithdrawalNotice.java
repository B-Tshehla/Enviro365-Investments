package com.enviro.assessment.grad001.boitumelotshehla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
public class WithdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "withdrawal_notice_seq")
    private Long id;
    private BigDecimal amount;
    @UpdateTimestamp
    private LocalDateTime withdrawalDate;
    private String bankName;
    private String accountType;
    private String branchCode;
    private String accountNumber;
    private String accountHolder;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

}
