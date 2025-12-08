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
        var loanApplication = loanApplicationService.createLoanApplication(LoanApplicationDto);
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @GetMapping("api/loan-applications")
    public ResponseEntity<List<LoanApplicationDto>> getAllLoanApplication(
        @RequestParam(required = false, defaultValue = "", name="status") String status,
        @RequestParam(required = false, defaultValue = "0", name="bankId") long bankId,
        @RequestParam(required = false, defaultValue = "0", name="customerId") long customerId
    )
    {
        var loanApplications = loanApplicationService.getAllLoanApplication(status, bankId, customerId);
        return new ResponseEntity<>(loanApplications, HttpStatus.OK);
    }

    @GetMapping("api/loan-applications/{id}")
    public ResponseEntity<LoanApplicationDto> getLoanApplicationById(@PathVariable Long id){
        var loanApplication = loanApplicationService.getLoanApplicationById(id);
        if (loanApplication == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);
    }

    @PutMapping("api/loan-applications/{id}/approve" )
    public ResponseEntity<LoanApplicationDto> approveLoanApplication(@PathVariable Long id){
        var loanApplication = loanApplicationService.approveLoanApplication(id);
        if (loanApplication == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(loanApplication.getStatus().toString().equals("APPROVED")){
            return new ResponseEntity<>(loanApplication, HttpStatus.OK);
        }
        return new ResponseEntity<>(loanApplication, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/loan-applications/{id}/reject" )
    public ResponseEntity<LoanApplicationDto> rejectLoanApplication(@PathVariable Long id){
        var loanApplication = loanApplicationService.rejectLoanApplication(id);
        if (loanApplication == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loanApplication, HttpStatus.OK);

    }

}
