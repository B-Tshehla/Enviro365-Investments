package com.enviro.assessment.grad001.boitumelotshehla.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

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
