package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.constants.ReportingApiEndpointConstants;
import com.intern.digitallendingsystem.dto.ReportingBankApiDto;
import com.intern.digitallendingsystem.dto.ReportingCustomerApiDto;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.model.LoanApplication;
import com.intern.digitallendingsystem.service.ReportingApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class ReportingApiController {
    ReportingApiService reportingApiService;


    @GetMapping(ReportingApiEndpointConstants.LOAN_SUMMARY)
    public ResponseEntity<ReportingBankApiDto> loanSummary(@PathVariable long id) {

        ReportingBankApiDto reportingBankApiDto = reportingApiService.bankLoanSummary(id);

        return new ResponseEntity<>(reportingBankApiDto, HttpStatus.OK);
    }


    @GetMapping(ReportingApiEndpointConstants.CUSTOMER_LOAN_SUMMARY)
    public ResponseEntity<ReportingCustomerApiDto> customerLoanSummary(@PathVariable long id) {

        ReportingCustomerApiDto reportingCustomerApiDto = reportingApiService.customerLoanSummary(id);

        return new ResponseEntity<>(reportingCustomerApiDto, HttpStatus.OK);
    }

//    @GetMapping("/api/reports/banks/{bankId}/high-outstanding")
//    public ResponseEntity<?> getHighOutstandingLoans(
//            @PathVariable long bankId,
//            @RequestParam LocalDate threshold) {
//
//        return reportingApiService.getOverdueLoans(bankId, threshold);
//    }
}
