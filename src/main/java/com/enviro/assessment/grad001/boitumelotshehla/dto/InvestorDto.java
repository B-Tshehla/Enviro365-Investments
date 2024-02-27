package com.enviro.assessment.grad001.boitumelotshehla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InvestorDto {
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
