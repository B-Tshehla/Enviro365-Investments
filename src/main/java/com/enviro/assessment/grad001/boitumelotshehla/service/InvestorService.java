package com.enviro.assessment.grad001.boitumelotshehla.service;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;

/**
 * The InvestorService interface provides methods for managing investors.
 * This includes creating, retrieving, updating investors, and getting the age of an investor.
 */
public interface InvestorService {

    /**
     * Creates a new investor based on the provided InvestorDto.
     *
     * @param investorDto The data transfer object containing information for the new investor.
     * @return The InvestorDto representing the newly created investor.
     */
    InvestorDto createInvestor(InvestorDto investorDto);

    /**
     * Finds and retrieves an investor by their unique identifier.
     *
     * @param investorId The unique identifier of the investor.
     * @return The InvestorDto representing the found investor.
     */
    InvestorDto findInvestorById(Long investorId);

    /**
     * Updates an existing investor based on the provided InvestorDto and investor ID.
     *
     * @param investorId  The unique identifier of the investor to be updated.
     * @param investorDto The data transfer object containing updated information for the investor.
     * @return The InvestorDto representing the updated investor.
     */
    InvestorDto updateInvestorById(Long investorId, InvestorDto investorDto);

    /**
     * Gets the age of an investor based on their unique identifier.
     *
     * @param investorId The unique identifier of the investor.
     * @return The age of the investor.
     */
    Integer getInvestorAge(Long investorId);
}
