package com.enviro.assessment.grad001.boitumelotshehla.businesscontrol;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;

/**
 * The InvestorValidation interface provides methods for validating investor data.
 * This includes validating mandatory investor data and optional data.
 */
public interface InvestorValidation {

    /**
     * Validates mandatory data for creating or updating an investor.
     *
     * @param investorDto The data transfer object containing investor information to be validated.
     */
    void validateInvestorData(InvestorDto investorDto);

    /**
     * Performs optional data validation for an investor.
     * This method can be used to validate any optional data specific to the investor.
     *
     * @param investorDto The data transfer object containing optional investor information to be validated.
     */
    void optionalDataValidation(InvestorDto investorDto);
}

