package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankUserDto;

import java.util.List;

public interface BankUserService {
    public BankUserDto createBankUser(BankUserDto bankUserDto);
    public List<BankUserDto> getAllBankUsers(String sort);
    public BankUserDto getBankUserById(long id);
    public BankUserDto updateBankUser(long id, BankUserDto bankUserDto);
    public Boolean deleteBankUser(long id);
}
