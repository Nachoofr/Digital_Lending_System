package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.CustomerDto;
import com.intern.digitallendingsystem.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers(String sort);
    ResponseEntity<CustomerDto> getCustomerById(long id);
    ResponseEntity<CustomerDto> updateCustomer(long id, CustomerDto customerDto);
    ResponseEntity<Void> deleteCustomer(long id);

}
