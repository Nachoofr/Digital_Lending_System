package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.dto.BankUserDto;
import com.intern.digitallendingsystem.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankUserController {
    @Autowired
    BankUserService bankUserService;

    @PostMapping("api/bankUsers")
    public ResponseEntity<BankUserDto> createBankUser(@RequestBody BankUserDto bankUserDto) {
        return (bankUserService.createBankUser(bankUserDto));
    }

    @GetMapping("api/bankUsers")
    public ResponseEntity<List<BankUserDto>> getAllBanks(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ) {
        return new ResponseEntity<>(bankUserService.getAllBankUsers(sort), HttpStatus.OK);
    }

    @GetMapping("api/bankUsers/{id}")
    public ResponseEntity<BankUserDto> getBankUser(@PathVariable long id) {
        return (bankUserService.getBankUserById(id));
    }

    @PutMapping("api/bankUsers/{id}")
    public ResponseEntity<BankUserDto> updateBankUser(
            @PathVariable long id,
            @RequestBody BankUserDto bankUserDto
    ){
        return bankUserService.updateBankUser(id, bankUserDto);
    }

    @DeleteMapping("api/bankUsers/{id}")
    public ResponseEntity<Void> deleteBankUser(@PathVariable long id) {
        return bankUserService.deleteBankUser(id);
    }



}
