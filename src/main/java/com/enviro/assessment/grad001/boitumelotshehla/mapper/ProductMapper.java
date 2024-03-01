package com.enviro.assessment.grad001.boitumelotshehla.mapper;

import com.enviro.assessment.grad001.boitumelotshehla.dto.ProductDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.model.enums.ProductType;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "productDto.productId", target = "id")
    @Mapping(source = "productDto.investorId", target = "investor.id")
    @Mapping(source = "investor", target = "investor")
    @Mapping(source = "productDto.type", target = "type",
            qualifiedByName = "productDtoToProductEntity")
    Product toEntity(ProductDto productDto, Investor investor);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "investor.id", target = "investorId")
    @Mapping(source = "product.type.value", target = "type")
    ProductDto toDto(Product product);

    @Mapping(source = "id", target = "productDto.productId")
    @Mapping(source = "investor.id", target = "investorId")
    @Mapping(source = "product.type.value", target = "type")
    List<ProductDto> toDtoList(List<Product> productList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "currentBalance", source = "currentBalance", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromDto(ProductDto productDto, @MappingTarget Product product);


    @Named("productDtoToProductEntity")
    static ProductType docTypeDtoToDocTypeEntity(String productType) {
        if (StringUtils.isEmpty(productType)) {
            return null;
        }
        return ProductType.enumValueOf(productType);
    }


}
