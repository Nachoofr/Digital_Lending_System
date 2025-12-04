package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanProductDto;

import java.util.List;

public interface LoanProductService {
    public LoanProductDto createLoanProduct(LoanProductDto loanProductDto);
    public List<LoanProductDto> getAllLoanProducts(String sort);
    public LoanProductDto getLoanProductById(long id );
    public LoanProductDto updateLoanProduct(long id, LoanProductDto loanProductDto);
    public boolean deleteLoanProduct(long id);
}
