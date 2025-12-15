package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankUserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankUserService {
    ResponseEntity<BankUserDto> createBankUser(BankUserDto bankUserDto);
    List<BankUserDto> getAllBankUsers(String sort);
    ResponseEntity<BankUserDto> getBankUserById(long id);
    ResponseEntity<BankUserDto> updateBankUser(long id, BankUserDto bankUserDto);
    ResponseEntity<Void> deleteBankUser(long id);
}
