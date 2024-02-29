package com.enviro.assessment.grad001.boitumelotshehla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InvestorDto {
    private Long InvestorId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String idNumber;
    private String email;
    private String streetNumber;
    private String streetName;
    private String city;
    private String postalCode;
    private String province;
}
