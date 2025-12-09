package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.CustomerBankAccountDto;
import com.intern.digitallendingsystem.service.CustomerBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerBankAccountController {
    CustomerBankAccountService customerBankAccountService;

    @PostMapping("/api/customer-bank-accounts")
    public ResponseEntity<CustomerBankAccountDto> createCustomerBankAccount(@RequestBody CustomerBankAccountDto customerBankAccountDto){
        return customerBankAccountService.createCustomerBankAccount(customerBankAccountDto);
    }

    @GetMapping("/api/customer-bank-accounts")
    public ResponseEntity<List<CustomerBankAccountDto>> getCustomerBankAccount(){
        var customerBankAccounts = customerBankAccountService.getCustomerBankAccount();
        return new ResponseEntity<>(customerBankAccounts, HttpStatus.OK);
    }

    @GetMapping("/api/customer-bank-accounts/{id}")
    public ResponseEntity<CustomerBankAccountDto> getCustomerBankAccountById(@PathVariable Long id){
        return customerBankAccountService.getCustomerBankAccountById(id);
    }
}
