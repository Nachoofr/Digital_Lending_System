package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankDto;

import java.util.List;

public interface BankService {
    BankDto createBank(BankDto bankDto);
    List<BankDto> getAllBanks();
    BankDto getBankById(long id);
    BankDto updateBank(long id, BankDto bankDto);
    boolean deleteBank(long id);

}
