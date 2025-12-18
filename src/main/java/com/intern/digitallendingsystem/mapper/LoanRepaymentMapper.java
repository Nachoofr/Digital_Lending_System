package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import com.intern.digitallendingsystem.model.LoanRepayment;
import com.intern.digitallendingsystem.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {MapperUtil.class})
public interface LoanRepaymentMapper {

    @Mapping(source="loanApplicationId.id", target="loanApplicationId")
    LoanRepaymentDto toDto(LoanRepayment loanRepayment);

    @Mapping(source = "loanApplicationId", target = "loanApplicationId", ignore = true)
    @Mapping(source = "paymentMethod", target = "paymentMethod", qualifiedByName = "mapRepaymentMethod")
    LoanRepayment toEntity(LoanRepaymentDto loanRepaymentDto);
}
