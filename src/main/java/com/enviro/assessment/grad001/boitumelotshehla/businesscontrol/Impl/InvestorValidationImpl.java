package com.enviro.assessment.grad001.boitumelotshehla.businesscontrol.Impl;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;


import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class InvestorValidationImpl {
    static String NAME_REGEX = "^[a-zA-Z]+$";
    static String STREET_NAME_REGEX = "^[a-zA-Z ]+$";
    static String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    static String ID_NUMBER_REGEX = "^(0[0-9]|[1-9][0-9])(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{7}$";
    static String POSTAL_CODE_REGEX = "^(?!0000)\\d{4}$";
    static String STREET_NUMBER_REGEX = "^[1-9]\\d*$";

    public void validateInvestorData(InvestorDto investorDto) {
        log.debug("validateInvestorData() - start: investorDto = {}", investorDto);
        List<String> invalidFields = new ArrayList<>();

        validateField(investorDto.getFirstName(), "FirstName", NAME_REGEX, invalidFields);
        validateOptionalField(investorDto.getMiddleName(), "MiddleName", NAME_REGEX, invalidFields);
        validateField(investorDto.getLastName(), "LastName", NAME_REGEX, invalidFields);
        validateField(investorDto.getStreetName(), "StreetName", STREET_NAME_REGEX, invalidFields);
        validateField(investorDto.getCity(), "CityName", NAME_REGEX, invalidFields);
        validateField(investorDto.getProvince(), "ProvinceName", NAME_REGEX, invalidFields);
        validateField(investorDto.getEmail(), "Email", EMAIL_REGEX, invalidFields);
        validateField(investorDto.getIdNumber(), "IDNumber", ID_NUMBER_REGEX, invalidFields);
        validateField(investorDto.getPostalCode(), "PostalCode", POSTAL_CODE_REGEX, invalidFields);
        validateField(investorDto.getStreetNumber(), "StreetNumber", STREET_NUMBER_REGEX, invalidFields);

        if (!invalidFields.isEmpty()) {
            String invalidFieldsMessage = "Invalid fields: " + String.join(", ", invalidFields);
            throw new ValidationException(invalidFieldsMessage);
        }
        log.debug("validateInvestorData() - end:");
    }

    public void optionalDataValidation(InvestorDto investorDto){
        log.debug("optionalDataValidation() - start: investorDto = {}",investorDto);
        List<String> invalidFields = new ArrayList<>();
        validateOptionalField(investorDto.getFirstName(), "FirstName", NAME_REGEX, invalidFields);
        validateOptionalField(investorDto.getMiddleName(), "MiddleName", NAME_REGEX, invalidFields);
        validateOptionalField(investorDto.getLastName(), "LastName", NAME_REGEX, invalidFields);
        validateOptionalField(investorDto.getStreetName(), "StreetName", STREET_NAME_REGEX, invalidFields);
        validateOptionalField(investorDto.getCity(), "CityName", NAME_REGEX, invalidFields);
        validateOptionalField(investorDto.getProvince(), "ProvinceName", NAME_REGEX, invalidFields);
        validateOptionalField(investorDto.getEmail(), "Email", EMAIL_REGEX, invalidFields);
        validateOptionalField(investorDto.getIdNumber(), "IDNumber", ID_NUMBER_REGEX, invalidFields);
        validateOptionalField(investorDto.getPostalCode(), "PostalCode", POSTAL_CODE_REGEX, invalidFields);
        validateOptionalField(investorDto.getStreetNumber(), "StreetNumber", STREET_NUMBER_REGEX, invalidFields);

        if (!invalidFields.isEmpty()) {
            String invalidFieldsMessage = "Invalid fields: " + String.join(", ", invalidFields);
            throw new ValidationException(invalidFieldsMessage);
        }
        log.debug("optionalDataValidation() - end:");
    }

    private void validateField(String fieldValue, String fieldName, String regex, List<String> invalidFields) {
        if (StringUtils.isEmpty(fieldValue) || !fieldValue.matches(regex)) {
            invalidFields.add(fieldName);
        }
    }

    private void validateOptionalField(String fieldValue, String fieldName, String regex, List<String> invalidFields) {
        if (fieldValue != null && !fieldValue.matches(regex)) {
            invalidFields.add(fieldName);
        }
    }
}
