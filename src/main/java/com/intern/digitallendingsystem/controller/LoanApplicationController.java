package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.LoanApplicationDto;
import com.intern.digitallendingsystem.service.LoanApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LoanApplicationController {
    LoanApplicationService loanApplicationService;

    @PostMapping("api/loan-applications")
    public ResponseEntity<LoanApplicationDto> createLoanApplication(@RequestBody LoanApplicationDto LoanApplicationDto){
        return loanApplicationService.createLoanApplication(LoanApplicationDto);
    }

    @GetMapping("api/loan-applications")//create a new dto and pass dto with post mapping
    public ResponseEntity<List<LoanApplicationDto>> getAllLoanApplication(
        @RequestParam(required = false, defaultValue = "", name="status") String status,
        @RequestParam(required = false, defaultValue = "0", name="bankId") long bankId,
        @RequestParam(required = false, defaultValue = "0", name="customerId") long customerId) {
        var loanApplications = loanApplicationService.getAllLoanApplication(status, bankId, customerId);
        return new ResponseEntity<>(loanApplications, HttpStatus.OK);
    }

    @GetMapping("api/loan-applications/{id}")
    public ResponseEntity<LoanApplicationDto> getLoanApplicationById(@PathVariable Long id){
       return loanApplicationService.getLoanApplicationById(id);
    }

    @PutMapping("api/loan-applications/{id}/approve" )//use post
    public ResponseEntity<LoanApplicationDto> approveLoanApplication(@PathVariable Long id){
        return loanApplicationService.approveLoanApplication(id);
    }

    @PutMapping("api/loan-applications/{id}/reject" )
    public ResponseEntity<LoanApplicationDto> rejectLoanApplication(@PathVariable Long id){
        return loanApplicationService.rejectLoanApplication(id);
    }

}
