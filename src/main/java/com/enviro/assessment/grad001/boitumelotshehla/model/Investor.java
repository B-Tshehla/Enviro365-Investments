package com.enviro.assessment.grad001.boitumelotshehla.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;


@Entity
@Accessors(chain = true)
@Data
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "investor_seq")
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String idNumber;
    private String email;
    private Integer streetNumber;
    private String streetName;
    private String city;
    private Integer postalCode;
    private String province;
}
