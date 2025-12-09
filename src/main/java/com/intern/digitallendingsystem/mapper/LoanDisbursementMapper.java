package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.LoanDisbursementDto;
import com.intern.digitallendingsystem.model.LoanDisbursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoanDisbursementMapper {

    @Mapping(source = "loanApplicationId.id", target = "loanApplicationId")
    LoanDisbursementDto toDto(LoanDisbursement loanDisbursement);

    @Mapping(target = "loanApplicationId", ignore = true)
    LoanDisbursement toEntity(LoanDisbursementDto loanDisbursementDto);
    @Mapping(target = "loanApplicationId", ignore = true)
    void update(LoanDisbursementDto dto, @MappingTarget LoanDisbursement entity);
}
