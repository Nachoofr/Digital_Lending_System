package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.LoanDisbursementDto;
import com.intern.digitallendingsystem.service.LoanDisbursementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoanDisbursementController {
    LoanDisbursementService loanDisbursementService;

    @PostMapping("/api/loan-applications/{id}/disburse")
    public ResponseEntity<LoanDisbursementDto> disbursLoan(
            @PathVariable long id,
            @RequestBody LoanDisbursementDto loanDisbursementDto) {
        return loanDisbursementService.disburse(id, loanDisbursementDto);
    }
}
