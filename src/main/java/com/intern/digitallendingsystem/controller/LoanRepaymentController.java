package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.constants.LoanRepaymentApiEndpointConstants;
import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import com.intern.digitallendingsystem.model.LoanRepayment;
import com.intern.digitallendingsystem.service.LoanRepaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LoanRepaymentController {
    LoanRepaymentService loanRepaymentService;

    @PostMapping(LoanRepaymentApiEndpointConstants.REPAYMENTS)
    public ResponseEntity<LoanRepaymentDto> makeRepayment(@PathVariable long id, @Valid @RequestBody LoanRepaymentDto loanRepaymentDto) {
        return loanRepaymentService.makeRepayment(id, loanRepaymentDto);
    }

    @GetMapping(LoanRepaymentApiEndpointConstants.REPAYMENTS)
    public ResponseEntity<List<LoanRepaymentDto>> getRepayments(@PathVariable long id) {
        return loanRepaymentService.getRepayments(id);
    }
}
