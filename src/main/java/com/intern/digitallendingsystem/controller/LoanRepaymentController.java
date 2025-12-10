package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.service.LoanRepaymentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanRepaymentController {
    LoanRepaymentService loanRepaymentService;
}
