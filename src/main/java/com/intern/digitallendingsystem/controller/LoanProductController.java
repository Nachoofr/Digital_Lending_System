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

    @GetMapping("api/loan-products/{id}")
    public ResponseEntity<LoanProductDto> getLoanProductById(@PathVariable long id){
        var loanProduct = loanProductService.getLoanProductById(id);
        if(loanProduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LoanProductDto>(loanProduct, HttpStatus.OK);
    }

    @PutMapping("api/loan-products/{id}")
    public ResponseEntity<LoanProductDto> updateLoanProduct(
            @PathVariable long id,
            @RequestBody LoanProductDto loanProductDto
    ){
        var laonProduct = loanProductService.updateLoanProduct(id, loanProductDto);
        if(laonProduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LoanProductDto>(laonProduct, HttpStatus.OK);
    }

    @DeleteMapping("api/loan-products/{id}")
    public ResponseEntity<Void> deleteLoanProduct(@PathVariable long id){
        var loanProduct = loanProductService.deleteLoanProduct(id);
        if (loanProduct == false){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
