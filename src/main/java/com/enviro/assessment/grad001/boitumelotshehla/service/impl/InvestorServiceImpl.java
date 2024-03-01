package com.enviro.assessment.grad001.boitumelotshehla.service.impl;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.exception.BirthDateExtractionException;
import com.enviro.assessment.grad001.boitumelotshehla.mapper.InvestorMapper;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import com.enviro.assessment.grad001.boitumelotshehla.service.InvestorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvestorServiceImpl implements InvestorService {

    private final InvestorMapper investorMapper;
    private final InvestorRepository investorRepository;
    private final String INVESTOR_NOT_FOUND = "Investor with id = %d is not in the database";

    @Override
    public InvestorDto createInvestor(InvestorDto investorDto) {
        log.debug("createInvestor() - start: investorDto = {}", investorDto);
        Investor investor = investorMapper.toEntity(investorDto);
        Investor savedInvestor = investorRepository.save(investor);
        InvestorDto savedInvestorDto = investorMapper.toDto(savedInvestor);
        log.debug("createInvestor() - end: savedInvestorDto = {}", savedInvestorDto);
        return savedInvestorDto;
    }

    @Override
    public InvestorDto findInvestorById(Long investorId) {
        log.debug("findInvestorById() - start: investorId = {}", investorId);
        Investor investor = findInvestor(investorId);
        InvestorDto investorDto = investorMapper.toDto(investor);
        log.debug("findInvestorById() - end: investorDto = {}", investorDto);
        return investorDto;
    }

    @Override
    public InvestorDto updateInvestorById(Long investorId, InvestorDto investorDto) {
        log.debug("updateInvestorById() - start: investorId = {}, investorDto = {}", investorId, investorDto);
        Investor investor = findInvestor(investorId);
        Investor updatedInvestor = investorMapper.updateInvestorFromDto(investorDto, investor);
        Investor saved = investorRepository.save(updatedInvestor);
        InvestorDto updatedInvestorDto = investorMapper.toDto(saved);
        log.debug("updateInvestorById() - end: updatedInvestorDto = {}", updatedInvestorDto);
        return updatedInvestorDto;
    }

    @Override
    public Integer getInvestorAge(Long investorId) {
        log.debug("getInvestorAge() - start: investorId = {}", investorId);
        Investor investor = findInvestor(investorId);
        LocalDate birthDate = extractBirthDate(investor.getIdNumber());
        Integer age = Period.between(birthDate, LocalDate.now()).getYears();
        log.debug("getInvestorAge() - end: age = {}", age);
        return age;
    }

    private Investor findInvestor(Long investorId) {
        return investorRepository.findById(investorId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(INVESTOR_NOT_FOUND, investorId)));
    }

    private LocalDate extractBirthDate(String idNumber) {
        String yymmdd = idNumber.substring(0, 6);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        dateFormat.setLenient(false);
        Date date = null;
        try {
            date = dateFormat.parse(yymmdd);
        } catch (ParseException e) {
            throw new BirthDateExtractionException(String.format("Failed to parse yymmdd = %d to a date", yymmdd));
        }
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

}
