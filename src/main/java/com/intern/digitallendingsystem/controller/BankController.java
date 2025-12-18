package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.constants.BankApiEndpointConstants;
import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.model.Bank;
import com.intern.digitallendingsystem.service.BankService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {
    @Autowired
    BankService bankService;

    @GetMapping(BankApiEndpointConstants.BANKS)
    public ResponseEntity<List<BankDto>> getAllBanks() {
        List<BankDto> banks = bankService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @PostMapping(BankApiEndpointConstants.BANKS)
    public ResponseEntity<BankDto> createBank(@Valid @RequestBody BankDto bankDto) {
        bankService.createBank(bankDto);
        return new ResponseEntity<>(bankDto, HttpStatus.CREATED);
    }

    @GetMapping(BankApiEndpointConstants.BANK_ID)
    public ResponseEntity<BankDto> getBankById(@PathVariable long id) {
        return bankService.getBankById(id);
    }

    @PutMapping(BankApiEndpointConstants.BANK_ID)
    public ResponseEntity<BankDto> updateBank(@PathVariable long id, @Valid @RequestBody BankDto bankDto
    ) {
        return bankService.updateBank(id, bankDto);
    }

    @DeleteMapping(BankApiEndpointConstants.BANK_ID)
    public ResponseEntity<Void> deleteBank(@PathVariable long id) {
       return bankService.deleteBank(id);
    }

}