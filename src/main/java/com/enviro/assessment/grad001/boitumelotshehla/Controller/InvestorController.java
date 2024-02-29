package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.businesscontrol.InvestorValidation;
import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.service.InvestorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class InvestorController {
    private final InvestorService investorService;
    private final InvestorValidation investorValidation;

    @PostMapping("/investor")
    ResponseEntity<InvestorDto> createInvestor(@RequestBody InvestorDto investorDto) {
        log.debug("createInvestor() - start: investorDto = {}", investorDto);
        investorValidation.validateInvestorData(investorDto);
        InvestorDto savedInvestorDto = investorService.createInvestor(investorDto);
        log.debug("createInvestor() - end: savedInvestorDto = {}", savedInvestorDto);
        return ResponseEntity.ok(savedInvestorDto);
    }

    @GetMapping("/investor/{investor-id}")
    ResponseEntity<InvestorDto> findInvestorById(@PathVariable("investor-id") Long investorId) {
        log.debug("findInvestorById() - start: investorId = {}", investorId);
        InvestorDto investorDto = investorService.findInvestorById(investorId);
        log.debug("findInvestorById() - end: investorDto = {}", investorDto);
        return ResponseEntity.ok(investorDto);
    }

    @PutMapping("/investor/{investor-id}")
    ResponseEntity<InvestorDto> updateInvestorById(@PathVariable("investor-id") Long investorId, @RequestBody InvestorDto investorDto) {
        log.debug("updateInvestorById() - end: investorId = {}, investorDto = {}", investorId, investorDto);
        investorValidation.optionalDataValidation(investorDto);
        InvestorDto updateInvestorDto = investorService.updateInvestorById(investorId, investorDto);
        log.debug("updateInvestorById() - end: updatedInvestorDto = {}", updateInvestorDto);
        return ResponseEntity.ok(updateInvestorDto);
    }
}
