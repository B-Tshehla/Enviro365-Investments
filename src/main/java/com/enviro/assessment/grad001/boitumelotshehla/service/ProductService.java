package com.enviro.assessment.grad001.boitumelotshehla.service;

import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> findAllProductsByInvestorId(Long investorId);
    ProductDto findProductById(Long productId);
    ProductDto updateProductById(ProductDto productDto, Long productId);

}
