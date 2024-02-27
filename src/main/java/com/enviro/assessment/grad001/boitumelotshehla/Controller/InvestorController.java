package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Address;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvestorController {
    private final InvestorRepository investorRepository;
    @PostMapping("create/investor")
    Investor createInvestor(@RequestBody Investor investor){
        return investorRepository.save(investor);
    }

    @GetMapping("/investors")
    List<Investor> getAllInvestors(){
        return investorRepository.findAll();
    }

}
