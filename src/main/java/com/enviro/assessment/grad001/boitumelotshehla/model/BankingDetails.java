package com.enviro.assessment.grad001.boitumelotshehla.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class BankingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "banking_details_seq")
    private Long id;
    private String bankName;
    private String accountType;
    private String branchCode;
    private String accountNumber;
    private String accountHolder;

}
