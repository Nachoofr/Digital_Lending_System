package com.intern.digitallendingsystem.service;


import com.intern.digitallendingsystem.dto.CustomerBankAccountDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerBankAccountService {
    ResponseEntity<CustomerBankAccountDto> createCustomerBankAccount(CustomerBankAccountDto customerBankAccountDto);
    List<CustomerBankAccountDto> getCustomerBankAccount();
    ResponseEntity<CustomerBankAccountDto> getCustomerBankAccountById(Long id);
}
