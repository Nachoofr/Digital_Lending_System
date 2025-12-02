package com.intern.digitallendingsystem.controller;

import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.model.Bank;
import com.intern.digitallendingsystem.service.BankService;
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

    @PostMapping("/api/banks")
    public ResponseEntity<BankDto> createBank(@RequestBody BankDto bankDto) {
        bankService.createBank(bankDto);
        return new ResponseEntity<BankDto>(bankDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/banks")
    public ResponseEntity<List<BankDto>> getAllBanks() {
        List<BankDto> banks = bankService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @GetMapping("/api/banks/{id}")
    public ResponseEntity<BankDto> getBankById(@PathVariable long id) {
        BankDto bankDto = bankService.getBankById(id);
        if (bankDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

    @PutMapping("/api/banks/{id}")
    public ResponseEntity<BankDto> updateBank(
            @PathVariable long id,
            @RequestBody BankDto bankDto
    ) {
        BankDto bank = bankService.updateBank(id, bankDto);
        if (bank == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bankService.updateBank(id, bankDto), HttpStatus.OK);
    }

    @DeleteMapping("/api/banks/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable long id) {
        var bank = bankService.deleteBank(id);
        if (bank == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}