package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.constants.BankApiEndpointConstants;
import com.intern.digitallendingsystem.constants.BankUserApiEndpointConstants;
import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.dto.BankUserDto;
import com.intern.digitallendingsystem.service.BankUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankUserController {
    @Autowired
    BankUserService bankUserService;

    @PostMapping(BankUserApiEndpointConstants.BANK_USERS)
    public ResponseEntity<BankUserDto> createBankUser( @Valid @RequestBody BankUserDto bankUserDto) {
        return (bankUserService.createBankUser(bankUserDto));
    }

    @GetMapping(BankUserApiEndpointConstants.BANK_USERS)
    public ResponseEntity<List<BankUserDto>> getAllBanks(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ) {
        return new ResponseEntity<>(bankUserService.getAllBankUsers(sort), HttpStatus.OK);
    }

    @GetMapping(BankUserApiEndpointConstants.BANK_USER_ID)
    public ResponseEntity<BankUserDto> getBankUser(@PathVariable long id) {
        return (bankUserService.getBankUserById(id));
    }

    @PutMapping(BankUserApiEndpointConstants.BANK_USER_ID)
    public ResponseEntity<BankUserDto> updateBankUser(@PathVariable long id, @Valid @RequestBody BankUserDto bankUserDto
    ){
        return bankUserService.updateBankUser(id, bankUserDto);
    }

    @DeleteMapping(BankUserApiEndpointConstants.BANK_USER_ID)
    public ResponseEntity<Void> deleteBankUser(@PathVariable long id) {
        return bankUserService.deleteBankUser(id);
    }



}
