package com.intern.digitallendingsystem.service;


import com.intern.digitallendingsystem.dto.LoanApplicationDto;
import com.intern.digitallendingsystem.dto.LoanProductDto;

import java.util.List;

public interface LoanApplicationService {
    LoanApplicationDto createLoanApplication(LoanApplicationDto loanApplicationDto);
    List<LoanApplicationDto> getAllLoanApplication(String status, long bankId, long customerId);
    LoanApplicationDto getLoanApplicationById(long id);
    LoanApplicationDto approveLoanApplication(long id);
    LoanApplicationDto rejectLoanApplication(long id);
}
