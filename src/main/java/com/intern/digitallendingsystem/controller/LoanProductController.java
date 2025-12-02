package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.LoanProductDto;
import com.intern.digitallendingsystem.service.LoanProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LoanProductController {
    LoanProductService loanProductService;

    @PostMapping("api/loan-products")
    public LoanProductDto createLoanProduct(@RequestBody LoanProductDto loanProductDto){
        return loanProductService.createLoanProduct(loanProductDto);
    }

    @GetMapping("api/loan-products")
    public ResponseEntity<List<LoanProductDto>> getLoanProduct(
            @RequestParam(required = false, defaultValue = "", name="sort")String sort
    ){
        return new ResponseEntity<List<LoanProductDto>>(loanProductService.getAllLoanProducts(sort), HttpStatus.OK);
    }

}
