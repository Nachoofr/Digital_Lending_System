package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.constants.CustomerApiEndpointConstants;
import com.intern.digitallendingsystem.constants.CustomerBankAccountApiEndpointConstants;
import com.intern.digitallendingsystem.dto.CustomerBankAccountDto;
import com.intern.digitallendingsystem.service.CustomerBankAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerBankAccountController {
    CustomerBankAccountService customerBankAccountService;

    @PostMapping(CustomerBankAccountApiEndpointConstants.CUSTOMER_BANK_ACCOUNTS)
    public ResponseEntity<CustomerBankAccountDto> createCustomerBankAccount(@Valid @RequestBody CustomerBankAccountDto customerBankAccountDto){
        return customerBankAccountService.createCustomerBankAccount(customerBankAccountDto);
    }

    @GetMapping(CustomerBankAccountApiEndpointConstants.CUSTOMER_BANK_ACCOUNTS)
    public ResponseEntity<List<CustomerBankAccountDto>> getCustomerBankAccount(){
        var customerBankAccounts = customerBankAccountService.getCustomerBankAccount();
        return new ResponseEntity<>(customerBankAccounts, HttpStatus.OK);
    }

    @GetMapping(CustomerBankAccountApiEndpointConstants.CUSTOMER_BANK_ACCOUNT_ID)
    public ResponseEntity<CustomerBankAccountDto> getCustomerBankAccountById(@PathVariable Long id){
        return customerBankAccountService.getCustomerBankAccountById(id);
    }
}
