package com.enviro.assessment.grad001.boitumelotshehla.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

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
    private LocalDateTime withdrawalDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_details_id")
    private BankingDetails bankingDetails;
    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
