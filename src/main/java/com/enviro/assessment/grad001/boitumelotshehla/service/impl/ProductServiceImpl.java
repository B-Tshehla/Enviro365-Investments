package com.enviro.assessment.grad001.boitumelotshehla.service.impl;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;
import com.enviro.assessment.grad001.boitumelotshehla.mapper.ProductMapper;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import com.enviro.assessment.grad001.boitumelotshehla.repository.ProductRepository;
import com.enviro.assessment.grad001.boitumelotshehla.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final InvestorRepository investorRepository;

    private final String INVESTOR_NOT_FOUND = "Investor with id = %d is not in the database";
    private final String PRODUCT_NOT_FOUND = "Product with id = %d is not in the database";

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.debug("createProduct() - start: productDto = {}",productDto);
        ProductType.valueOf(productDto.getType());
        Investor investor = investorRepository.findById(productDto.getInvestorId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(INVESTOR_NOT_FOUND, productDto.getInvestorId())));
        Product product = productMapper.toEntity(productDto, investor);
        Product savedProduct = productRepository.save(product);
        ProductDto savedProductDto = productMapper.toDto(savedProduct);
        log.debug("createProduct() - end: savedProductDto = {}", savedProductDto);
        return savedProductDto;
    }

    @Override
    public List<ProductDto> findAllProductsByInvestorId(Long investorId) {
        log.debug("findProductsByInvestorId() - start: investorId = {}", investorId);
        List<Product> productList = productRepository.findAllByInvestorId(investorId);
        List<ProductDto> productDtoList = productMapper.toDtoList(productList);
        log.debug("findProductsByInvestorId() - end: productDtoList = {}", productDtoList);
        return productDtoList;
    }

    @Override
    public ProductDto findProductById(Long productId) {
        log.debug("findProductById() - start: productId = {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(PRODUCT_NOT_FOUND, productId)));
        ProductDto productDto = productMapper.toDto(product);
        log.debug("findProductById() - end: productDto = {}", productDto);
        return productDto;
    }

    @Override
    public ProductDto updateProductById(ProductDto productDto, Long productId) {
        log.debug("updateProductById() - start: productId = {}",productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(PRODUCT_NOT_FOUND, productId)));
        Product updatedProduct = productMapper.updateProductFromDto(productDto, product);
        Product savedProduct = productRepository.save(updatedProduct);
        ProductDto updatedProductDto = productMapper.toDto(savedProduct);
        log.debug("updateProductById() - end: updatedProductDto = {}", updatedProductDto);
        return updatedProductDto;
    }

}
