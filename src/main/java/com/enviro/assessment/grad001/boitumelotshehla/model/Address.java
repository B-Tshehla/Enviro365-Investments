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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "address_seq")
    private Long id;
    private Integer streetNumber;
    private String streetName;
    private String city;
    private Integer postalCode;
    private String province;
}
