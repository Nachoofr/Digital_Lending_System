package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.LoanApplicationDto;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.model.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {
    @Mapping(source ="bankId.id", target ="bankId")
    @Mapping(source = "loanProductId.id", target = "loanProductId")
    @Mapping(source = "customerId.id", target = "customerId")
    @Mapping(source = "customerBankAccountId.id", target = "customerBankAccountId")
    LoanApplicationDto toDto(LoanApplication loanApplication);

    @Mapping(source = "bankId", target = "bankId.id")
    @Mapping(source = "loanProductId", target = "loanProductId.id")
    @Mapping(source="customerId", target="customerId.id")
    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatus")
    @Mapping(source = "customerBankAccountId", target = "customerBankAccountId.id")
    LoanApplication toEntity(LoanApplicationDto loanApplicationDto);

    @Mapping(source = "bankId", target = "bankId.id")
    @Mapping(source = "loanProductId", target = "loanProductId.id")
    @Mapping(source = "customerId", target = "customerId.id")
    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatus")
    @Mapping(source = "customerBankAccountId", target = "customerBankAccountId.id")
    LoanApplication update(LoanApplicationDto loanApplicationDto, @MappingTarget LoanApplication loanApplication);


    @Named("mapStatus")
    default LoanStatus mapStatus(String status){
        if (status == null) return null;
        return LoanStatus.valueOf(status.toUpperCase().trim());
    }
}
