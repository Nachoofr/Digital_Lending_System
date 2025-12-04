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
    private final CustomerServiceImpl customerServiceImpl;
    CustomerService customerService;

    @PostMapping("/api/customers")
    public ResponseEntity<CustomerDto> createCustomer (@RequestBody CustomerDto customerDto) {
        var customer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.OK);
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
        var customers = customerService.getCustomerById(id);
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("api/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable long id,
            @RequestBody CustomerDto customerDto
    ){
        var customer = customerService.updateCustomer(id, customerDto);
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("api/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        var customer = customerService.deleteCustomer(id);
        if (customer == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
