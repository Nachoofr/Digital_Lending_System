package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import com.intern.digitallendingsystem.model.LoanRepayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoanRepaymentMapper {

    @Mapping(source="loanApplicationId.id", target="loanApplicationId")
    LoanRepaymentDto toDto(LoanRepayment loanRepayment);

    @Mapping(source = "loanApplicationId", target = "loanApplicationId", ignore = true)
    @Mapping(source = "paymentMethod", target = "paymentMethod", qualifiedByName = "mapRepaymentMethod")
    LoanRepayment toEntity(LoanRepaymentDto loanRepaymentDto);

    @Named("mapRepaymentMethod")
    default String mapRepaymentMethod(String repaymentMethod) {
        if (repaymentMethod == null) return null;
        return repaymentMethod.toUpperCase().trim();
    }


}
