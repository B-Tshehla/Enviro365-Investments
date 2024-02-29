package com.enviro.assessment.grad001.boitumelotshehla.mapper;

import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "productDto.productId", target = "id")
    @Mapping(source = "productDto.investorId", target = "investor.id")
    @Mapping(source = "investor", target = "investor")
    Product toEntity(ProductDto productDto, Investor investor);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "investor.id", target = "investorId")
    ProductDto toDto(Product product);
    @Mapping(source = "productDto.productId", target = "id")
    @Mapping(source = "productDto.investorId", target = "investor.id")
    List<Product> toEntityList(List<ProductDto> productDtoList);

    @Mapping(source = "id", target = "productDto.productId")
    @Mapping(source = "investor.id", target = "investorId")
    List<ProductDto> toDtoList(List<Product> productList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "currentBalance", source = "currentBalance", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromDto(ProductDto productDto, @MappingTarget Product product);

}
