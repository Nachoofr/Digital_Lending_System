package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoanProductService {
    ResponseEntity<LoanProductDto> createLoanProduct(LoanProductDto loanProductDto);
    List<LoanProductDto> getAllLoanProducts(String sort);
    ResponseEntity<LoanProductDto> getLoanProductById(long id );
    ResponseEntity<LoanProductDto> updateLoanProduct(long id, LoanProductDto loanProductDto);
    ResponseEntity<Void> deleteLoanProduct(long id);
}
