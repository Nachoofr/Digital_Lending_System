package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.model.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface BankMapper{
    BankDto toDto(Bank bank);
    Bank toEntity(BankDto bankDto);
    void update(BankDto updateBankDto, @MappingTarget Bank bank);


}
