package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.CustomerDto;
import com.intern.digitallendingsystem.model.Customer;

import java.util.List;

public interface CustomerService {
    public CustomerDto createCustomer(CustomerDto customerDto);
    public List<CustomerDto> getAllCustomers(String sort);
    public CustomerDto getCustomerById(long id);
    public CustomerDto updateCustomer(long id, CustomerDto customerDto);
    public boolean deleteCustomer(long id);

}
