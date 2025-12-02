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
        BankUserDto bankUser = bankUserService.createBankUser(bankUserDto);
        return new ResponseEntity<BankUserDto>(bankUser, HttpStatus.CREATED);
    }

    @GetMapping("api/bankUsers")
    public ResponseEntity<List<BankUserDto>> getAllBanks(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ) {
        return new ResponseEntity<List<BankUserDto>>(bankUserService.getAllBankUsers(sort), HttpStatus.OK);
    }

    @GetMapping("api/bankUsers/{id}")
    public ResponseEntity<BankUserDto> getBankUser(@PathVariable long id) {
        var bankUser = bankUserService.getBankUserById(id);
        if (bankUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BankUserDto>(bankUser, HttpStatus.OK);
    }

    @PutMapping("api/bankUsers/{id}")
    public ResponseEntity<BankUserDto> updateBankUser(
            @PathVariable long id,
            @RequestBody BankUserDto bankUserDto
    ){
        var bankUser = bankUserService.updateBankUser(id, bankUserDto);
        if (bankUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BankUserDto>(bankUser, HttpStatus.OK);
    }

    @DeleteMapping("api/bankUsers/{id}")
    public ResponseEntity<Void> deleteBankUser(@PathVariable long id) {
        var bankUser = bankUserService.deleteBankUser(id);
        if (bankUser == false){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(HttpStatus.OK);
    }


}
