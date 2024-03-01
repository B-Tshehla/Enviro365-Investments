package com.enviro.assessment.grad001.boitumelotshehla.service;

import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;

import java.util.List;

/**
 * The ProductService interface provides methods for managing products.
 * This includes creating, retrieving, updating, and finding products based on investor ID.
 */
public interface ProductService {

    /**
     * Creates a new product based on the provided ProductDto.
     *
     * @param productDto The data transfer object containing information for the new product.
     * @return The ProductDto representing the newly created product.
     */
    ProductDto createProduct(ProductDto productDto);

    /**
     * Retrieves a list of products associated with a specific investor ID.
     *
     * @param investorId The unique identifier of the investor.
     * @return A list of ProductDto objects associated with the specified investor.
     */
    List<ProductDto> findAllProductsByInvestorId(Long investorId);

    /**
     * Finds and retrieves a product by its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @return The ProductDto representing the found product.
     */
    ProductDto findProductById(Long productId);

    /**
     * Updates an existing product based on the provided ProductDto and product ID.
     *
     * @param productDto The data transfer object containing updated information for the product.
     * @param productId  The unique identifier of the product to be updated.
     * @return The ProductDto representing the updated product.
     */
    ProductDto updateProductById(ProductDto productDto, Long productId);

}
