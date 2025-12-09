package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanDisbursementDto;
import org.springframework.http.ResponseEntity;

public interface LoanDisbursementService {
    ResponseEntity<LoanDisbursementDto> disburse(long id, LoanDisbursementDto loanDisbursementDto);
}
