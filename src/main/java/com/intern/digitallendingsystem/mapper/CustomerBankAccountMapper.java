package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.CustomerBankAccountDto;
import com.intern.digitallendingsystem.model.CustomerBankAccount;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerBankAccountMapper {
    @Mapping(target = "bankId", source = "bankId.id")
    @Mapping(target = "customerId", source = "customerId.id")
    CustomerBankAccountDto toDto(CustomerBankAccount customerBankAccount);

    @Mapping(target = "bankId.id", source = "bankId")
    @Mapping(target = "customerId.id", source = "customerId")
    CustomerBankAccount toEntity(CustomerBankAccountDto customerBankAccountDto);

    @Mapping(target = "bankId.id", source = "bankId")
    @Mapping(target = "customerId.id", source = "customerId")
    CustomerBankAccount update(CustomerBankAccountDto customerBankAccountDto, @MappingTarget CustomerBankAccount customerBankAccount);



}
