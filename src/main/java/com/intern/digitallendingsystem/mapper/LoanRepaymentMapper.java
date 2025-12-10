package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import com.intern.digitallendingsystem.model.LoanRepayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanRepaymentMapper {

    @Mapping(source="loanApplicationId.id", target="loanApplicationId")
    LoanRepaymentDto toDto(LoanRepayment loanRepayment);

    @Mapping(source = "loanApplicationId", target = "loanApplicationId", ignore = true)
    LoanRepayment toEntity(LoanRepaymentDto loanRepaymentDto);



}
