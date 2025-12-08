package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.CustomerDto;
import com.intern.digitallendingsystem.mapper.CustomerMapper;
import com.intern.digitallendingsystem.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        var customer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    public ResponseEntity<CustomerDto> updateCustomer(long id, CustomerDto customerDto) {
        var customer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerMapper.update(customerDto, customer);
        customer.setActive(true);
        customerRepo.save(customer);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteCustomer(long id) {
        var customer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setActive(false);
        customerRepo.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}