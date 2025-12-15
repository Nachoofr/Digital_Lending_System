package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankService {
    ResponseEntity<BankDto> createBank(BankDto bankDto);
    List<BankDto> getAllBanks();
    ResponseEntity<BankDto> getBankById(long id);
    ResponseEntity<BankDto> updateBank(long id, BankDto bankDto);
    ResponseEntity<Void> deleteBank(long id);

}
