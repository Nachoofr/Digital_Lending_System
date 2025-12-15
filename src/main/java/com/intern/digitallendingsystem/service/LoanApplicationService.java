package com.intern.digitallendingsystem.service;


import com.intern.digitallendingsystem.dto.LoanApplicationDto;
import com.intern.digitallendingsystem.dto.LoanProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoanApplicationService {
    ResponseEntity<LoanApplicationDto> createLoanApplication(LoanApplicationDto loanApplicationDto);
    List<LoanApplicationDto> getAllLoanApplication(String status, long bankId, long customerId);
    ResponseEntity<LoanApplicationDto> getLoanApplicationById(long id);
    ResponseEntity<LoanApplicationDto> approveLoanApplication(long id);
    ResponseEntity<LoanApplicationDto> rejectLoanApplication(long id);
}
