package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.AbstractIntegrationTest;
import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import com.enviro.assessment.grad001.boitumelotshehla.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest extends AbstractIntegrationTest {

    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    void createProductTest(){
        Investor investor = new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("Boitumelotshehla@gmail.com");
        Investor savedInvestor = investorRepository.save(investor);
        ProductDto requestDto = new ProductDto()
                .setInvestorId(savedInvestor.getId())
                .setName("Emergency Fund")
                .setType("Savings")
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
        Long investorId = 99L;
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

    @Test
    void getAllProductsByInvestorIdTest(){
        Investor investor = new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("Boitumelotshehla@gmail.com");
        investorRepository.save(investor);
        List<Product> products = List.of(
                new Product()
                        .setInvestor(investor)
                        .setName("Emergency Fund")
                        .setType(ProductType.SAVINGS)
                        .setCurrentBalance(BigDecimal.valueOf(5000.00)),
                new Product()
                        .setInvestor(investor)
                        .setName("Retirement Fund")
                        .setType(ProductType.RETIREMENT)
                        .setCurrentBalance(BigDecimal.valueOf(50000.00))
        );
        productRepository.saveAll(products);
        assertNotNull(investor);
        List<ProductDto> response = webTestClient.get()
                .uri("api/v1/products/{investor-id}", investor.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ProductDto.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(response);
        assertEquals(response.size(),2);
        assertEquals(response.get(0).getInvestorId(),products.get(0).getInvestor().getId());
    }

    @Test
    void getAllProductsByInvalidInvestorIdTest(){
        Long invalidId = 99L;
        Investor investor = new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("Boitumelotshehla@gmail.com");
        investorRepository.save(investor);
        Investor investor1 = investorRepository.save(new Investor().setFirstName("Test"));
        List<Product> products = List.of(
                new Product()
                        .setInvestor(investor)
                        .setName("Emergency Fund")
                        .setType(ProductType.SAVINGS)
                        .setCurrentBalance(BigDecimal.valueOf(5000.00)),
                new Product()
                        .setInvestor(investor)
                        .setName("Retirement Fund")
                        .setType(ProductType.RETIREMENT)
                        .setCurrentBalance(BigDecimal.valueOf(50000.00)),
                new Product()
                        .setInvestor(investor1)
                        .setName("Emergency Fund")
                        .setType(ProductType.SAVINGS)
                        .setCurrentBalance(BigDecimal.valueOf(5000.00))
        );
        productRepository.saveAll(products);
        assertNotNull(investor);
        List<ProductDto> response = webTestClient.get()
                .uri("api/v1/products/{investor-id}", invalidId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ProductDto.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(response);
        assertEquals(response.size(),0);
    }

    @Test
    void getProductByIdTest(){
        Investor investor = new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("Boitumelotshehla@gmail.com");
        investorRepository.save(investor);
        Product product = new Product()
                .setInvestor(investor)
                .setName("Emergency Fund")
                .setType(ProductType.SAVINGS)
                .setCurrentBalance(BigDecimal.valueOf(5000.00));

        Product savedProduct = productRepository.save(product);

        ProductDto response = webTestClient.get()
                .uri("api/v1/product/{product-id}", savedProduct.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertEquals(response.getProductId(),savedProduct.getId());
        assertEquals(response.getInvestorId(),savedProduct.getInvestor().getId());
        assertEquals(response.getName(),savedProduct.getName());
        assertEquals(response.getType(),savedProduct.getType().getValue());
        assertEquals(response.getCurrentBalance(),savedProduct.getCurrentBalance().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void getProductByInvalidIdTest(){
        Long invalidId = 99L;

        webTestClient.get()
                .uri("api/v1/product/{product-id}", invalidId)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void updateProductByIdTest(){
        Investor investor = new Investor()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("Boitumelotshehla@gmail.com");
        investorRepository.save(investor);
        Product product = new Product()
                .setInvestor(investor)
                .setName("Emergency Fund")
                .setType(ProductType.SAVINGS)
                .setCurrentBalance(BigDecimal.valueOf(5000.00));
        productRepository.save(product);

        ProductDto productDto = new ProductDto().setName("Vacation");

        ProductDto response = webTestClient.put()
                .uri("api/v1/product/{product-id}", product.getId())
                .bodyValue(productDto)
                .exchange()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertEquals(response.getProductId(),product.getId());
        assertEquals(response.getInvestorId(),product.getInvestor().getId());
        assertEquals(response.getName(),productDto.getName());
        assertEquals(response.getType(),product.getType().getValue());
        assertEquals(response.getCurrentBalance(),product.getCurrentBalance().setScale(2, RoundingMode.HALF_UP));

    }

}