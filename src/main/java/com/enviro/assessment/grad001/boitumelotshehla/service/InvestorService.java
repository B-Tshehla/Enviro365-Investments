package com.enviro.assessment.grad001.boitumelotshehla.service;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;

public interface InvestorService {

    InvestorDto createInvestor(InvestorDto investorDto);
    InvestorDto findInvestorById(Long investorId);
    InvestorDto updateInvestorById(Long investorId, InvestorDto investorDto);
    Integer getInvestorAge(Long investorId);
}
