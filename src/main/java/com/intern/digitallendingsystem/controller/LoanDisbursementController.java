package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.constants.LoanDisbursementApiEndpointConstants;
import com.intern.digitallendingsystem.dto.LoanDisbursementDto;
import com.intern.digitallendingsystem.service.LoanDisbursementService;
import jakarta.validation.Valid;
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

    @PostMapping(LoanDisbursementApiEndpointConstants.DISBURSE)
    public ResponseEntity<LoanDisbursementDto> disburseLoan(@PathVariable long id, @Valid @RequestBody LoanDisbursementDto loanDisbursementDto) {
        return loanDisbursementService.disburse(id, loanDisbursementDto);
    }
}
