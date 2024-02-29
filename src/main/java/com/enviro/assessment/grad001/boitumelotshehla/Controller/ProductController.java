package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import com.enviro.assessment.grad001.boitumelotshehla.repository.ProductRepository;
import com.enviro.assessment.grad001.boitumelotshehla.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    ProductDto createProduct(@RequestBody ProductDto productDto) {
        log.debug("createProduct() - start: productDto = {}", productDto);
        ProductDto product = productService.createProduct(productDto);
        log.debug("createProduct() - end: product = {}", product);
        return product;
    }

    @GetMapping("/products/{investor-id}")
    List<ProductDto> getAllProductsByInvestorId(@PathVariable("investor-id") Long investorId) {
        log.debug("getAllProductsByInvestorId() - start: investorId = {}", investorId);
        List<ProductDto> productDtoList = productService.findAllProductsByInvestorId(investorId);
        log.debug("getAllProductsByInvestorId() - end: productDtoList = {}", productDtoList);
        return productDtoList;
    }

    @GetMapping("/product/{product-id}")
    ProductDto getProductById(@PathVariable("product-id") Long productId) {
        log.debug("getProductById() - start: productId = {}", productId);
        ProductDto productDto = productService.findProductById(productId);
        log.debug("getProductById() - end: productDto = {}", productDto);
        return productDto;
    }

    @PutMapping("/product/{product-id}")
    ProductDto updateProductById(@PathVariable("product-id") Long productId, @RequestBody ProductDto productDto){
        log.debug("updateProductById() - start: productId = {}", productId);
        ProductDto updatedProductDto = productService.updateProductById(productDto, productId);
        log.debug("updateProductById() - end: updatedProductDto = {}", updatedProductDto);
        return updatedProductDto;
    }

}
