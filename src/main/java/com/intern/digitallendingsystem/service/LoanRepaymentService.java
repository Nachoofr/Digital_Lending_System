package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoanRepaymentService {
    ResponseEntity<LoanRepaymentDto> makeRepayment(long id, LoanRepaymentDto loanRepaymentDto);
    ResponseEntity<List<LoanRepaymentDto>> getRepayments(long id);
}
