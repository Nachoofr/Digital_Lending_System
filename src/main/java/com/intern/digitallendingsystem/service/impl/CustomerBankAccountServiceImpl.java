package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.CustomerBankAccountDto;
import com.intern.digitallendingsystem.mapper.CustomerBankAccountMapper;
import com.intern.digitallendingsystem.model.Customer;
import com.intern.digitallendingsystem.model.CustomerBankAccount;
import com.intern.digitallendingsystem.repository.CustomerBankAccountRepo;
import com.intern.digitallendingsystem.repository.CustomerRepo;
import com.intern.digitallendingsystem.service.CustomerBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerBankAccountServiceImpl implements CustomerBankAccountService {
     CustomerBankAccountRepo customerBankAccountRepo;
     CustomerBankAccountMapper customerBankAccountMapper;
     CustomerRepo customerRepo;

    public ResponseEntity<CustomerBankAccountDto> createCustomerBankAccount(CustomerBankAccountDto customerBankAccountDto){
        var customerBankAccount = customerBankAccountMapper.toEntity(customerBankAccountDto);
        Optional<Customer> optionalCustomer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(customerBankAccount.getCustomerId().getId());
        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = optionalCustomer.get();
        customerBankAccount.setBankId(customer.getBankId());
        customerBankAccount.setActive(true);
        customerBankAccountRepo.save(customerBankAccount);
        return new ResponseEntity<>(customerBankAccountMapper.toDto(customerBankAccount), HttpStatus.CREATED);
    }

    public List<CustomerBankAccountDto> getCustomerBankAccount(){
        return customerBankAccountRepo.findAllByCustomerIdIsActiveTrueAndIsActiveTrue().stream()
                .map(customerBankAccountMapper::toDto)
                .toList();
    }

    public ResponseEntity<CustomerBankAccountDto> getCustomerBankAccountById(Long id){
        Optional<CustomerBankAccount> optionalCustomerBankAccount = customerBankAccountRepo.findByIdAndIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (optionalCustomerBankAccount.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CustomerBankAccount customerBankAccount = optionalCustomerBankAccount.get();
        return new ResponseEntity<>(customerBankAccountMapper.toDto(customerBankAccount), HttpStatus.OK);
    }

}
