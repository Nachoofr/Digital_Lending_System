package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.mapper.LoanRepaymentMapper;
import com.intern.digitallendingsystem.repository.LoanRepaymentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanRepaymentServiceImpl implements LoanRepaymentService {
    LoanRepaymentRepo loanRepaymentRepo;
    LoanRepaymentMapper loanRepaymentMapper;
}
