package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import com.intern.digitallendingsystem.model.LoanRepayment;
import com.intern.digitallendingsystem.service.LoanRepaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LoanRepaymentController {
    LoanRepaymentService loanRepaymentService;

    @PostMapping("/api/loan-applications/{id}/repayments")
    public ResponseEntity<LoanRepaymentDto> makeRepayment(
            @PathVariable long id,
            @RequestBody LoanRepaymentDto loanRepaymentDto) {
        return loanRepaymentService.makeRepayment(id, loanRepaymentDto);
    }

    @GetMapping("/api/loan-applications/{id}/repayments")
    public ResponseEntity<List<LoanRepaymentDto>> getRepayments(@PathVariable long id) {
        return loanRepaymentService.getRepayments(id);
    }
}
