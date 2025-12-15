package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.CustomerBankAccountDto;
import com.intern.digitallendingsystem.mapper.CustomerBankAccountMapper;
import com.intern.digitallendingsystem.repository.CustomerBankAccountRepo;
import com.intern.digitallendingsystem.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerBankAccountServiceImpl implements CustomerBankAccountService {
     CustomerBankAccountRepo customerBankAccountRepo;
     CustomerBankAccountMapper customerBankAccountMapper;
     CustomerRepo customerRepo;

    public ResponseEntity<CustomerBankAccountDto> createCustomerBankAccount(CustomerBankAccountDto customerBankAccountDto){
        var customerBankAccount = customerBankAccountMapper.toEntity(customerBankAccountDto);
        var customer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(customerBankAccount.getCustomerId().getId());
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
        var customerBankAccount = customerBankAccountRepo.findByIdAndIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (customerBankAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerBankAccountMapper.toDto(customerBankAccount), HttpStatus.OK);
    }

}
