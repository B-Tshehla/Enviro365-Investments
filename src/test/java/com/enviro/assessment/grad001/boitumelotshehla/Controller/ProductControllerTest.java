package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.AbstractIntegrationTest;
import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest extends AbstractIntegrationTest {

    @Autowired
    InvestorRepository investorRepository;
    @BeforeEach
    void setUp() {
        Investor investor = new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("Boitumelotshehla@gmail.com");
        investorRepository.save(investor);
    }

    @Test
    void createProductTest(){
        Long investorId = 1L;
        ProductDto requestDto = new ProductDto()
                .setInvestorId(investorId)
                .setName("Emergency Fund")
                .setType("SAVINGS")
                .setCurrentBalance(BigDecimal.valueOf(5000.00));

        ProductDto response = webTestClient.post()
                .uri("api/v1/product")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertEquals(response.getName(),requestDto.getName());
        assertEquals(response.getType(),requestDto.getType());
        assertEquals(response.getCurrentBalance(),requestDto.getCurrentBalance());
    }

    @Test
    void createProductWithInvalidInvestorId(){
        Long investorId = 2L;
        ProductDto requestDto = new ProductDto()
                .setInvestorId(investorId)
                .setName("Emergency Fund")
                .setType("SAVINGS")
                .setCurrentBalance(BigDecimal.valueOf(5000.00));

        webTestClient.post()
                .uri("api/v1/product")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void validateProductTypeTest(){
        Long investorId = 1L;
        ProductDto requestDto = new ProductDto()
                .setInvestorId(investorId)
                .setName("Emergency Fund")
                .setType("CASH")
                .setCurrentBalance(BigDecimal.valueOf(5000.00));

        webTestClient.post()
                .uri("api/v1/product")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

}