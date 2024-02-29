package com.enviro.assessment.grad001.boitumelotshehla.mapper;

import com.enviro.assessment.grad001.boitumelotshehla.dto.WithdrawalNoticeDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import com.enviro.assessment.grad001.boitumelotshehla.model.WithdrawalNotice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WithdrawalNoticeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "withdrawalNoticeDto.withdrawal", target = "amount")
    @Mapping(source = "product", target = "product")
    WithdrawalNotice toEntity(WithdrawalNoticeDto withdrawalNoticeDto, Product product);


}
