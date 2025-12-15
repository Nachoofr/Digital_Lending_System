package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.CustomerDto;
import com.intern.digitallendingsystem.service.CustomerService;
import com.intern.digitallendingsystem.service.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {
    CustomerService customerService;

    @PostMapping("/api/customers")
    public ResponseEntity<CustomerDto> createCustomer (@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/api/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(
            @RequestParam(required = false, defaultValue = "", name="sort")String sort
    ) {
        var customers = customerService.getAllCustomers(sort);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("api/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("api/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable long id,
            @RequestBody CustomerDto customerDto
    ){
       return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("api/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        return customerService.deleteCustomer(id);
    }



}
