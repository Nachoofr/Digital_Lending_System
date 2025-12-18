package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.CustomerDto;
import com.intern.digitallendingsystem.mapper.CustomerMapper;
import com.intern.digitallendingsystem.model.Customer;
import com.intern.digitallendingsystem.repository.CustomerRepo;
import com.intern.digitallendingsystem.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    CustomerRepo customerRepo;
    CustomerMapper customerMapper;

    // todo
    // implement proper logging using SL4J
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {
        var customer = customerMapper.toEntity(customerDto);
        customer.setActive(true);
        customerRepo.save(customer);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.CREATED);
    }

    public List<CustomerDto> getAllCustomers(String sort) {
        Sort sorting = (sort == null || sort.isBlank()) ? Sort.unsorted() : Sort.by(sort);
        return customerRepo.findAllByIsActiveTrueAndBankIdIsActiveTrue(sorting)
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public ResponseEntity<CustomerDto> getCustomerById(long id) {
        Optional<Customer> optionalCustomer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = optionalCustomer.get();
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    public ResponseEntity<CustomerDto> updateCustomer(long id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = optionalCustomer.get();
        customerMapper.update(customerDto, customer);
        customer.setActive(true);
        customerRepo.save(customer);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = optionalCustomer.get();
        customer.setActive(false);
        customerRepo.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}