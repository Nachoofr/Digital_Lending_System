package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.LoanProductDto;
import com.intern.digitallendingsystem.model.LoanProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface LoanProductMapper {
    @Mapping(source ="bankId.id", target ="bankId")
    LoanProductDto toDto(LoanProduct loanProduct);

    @Mapping(source ="bankId", target ="bankId.id")
    LoanProduct toEntity(LoanProductDto loanProductDto);

    @Mapping(source ="bankId", target ="bankId.id")
    LoanProduct update(LoanProductDto loanProductDto, @MappingTarget LoanProduct loanProduct);
}
