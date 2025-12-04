package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.CustomerDto;
import com.intern.digitallendingsystem.mapper.CustomerMapper;
import com.intern.digitallendingsystem.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    CustomerRepo customerRepo;
    CustomerMapper customerMapper;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        var customer = customerMapper.toEntity(customerDto);
        customer.setActive(true);
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> getAllCustomers(String sort) {
        Sort sorting = (sort == null || sort.isBlank()) ? Sort.unsorted() : Sort.by(sort);
        return customerRepo.findAllByIsActiveTrueAndBankIdIsActiveTrue(sorting)
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerDto getCustomerById(long id){
        var customer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (customer == null){
            return  null;
        }
        return customerMapper.toDto(customer);
    }

    public CustomerDto updateCustomer(long id, CustomerDto customerDto) {
        var customer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (customer == null) {
            return null;
        }
        customerMapper.update(customerDto, customer);
        customer.setActive(true);
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    public boolean deleteCustomer(long id){
        var customer = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (customer == null){
            return false;
        }
        customer.setActive(false);
        customerRepo.save(customer);
        return true;
    }


}