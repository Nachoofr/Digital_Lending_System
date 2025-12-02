package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankUserDto;
import com.intern.digitallendingsystem.mapper.BankUserMapper;
import com.intern.digitallendingsystem.repository.BankUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private BankUserMapper bankUserMapper;
    private BankUserRepo bankUserRepo;

    public BankUserDto createBankUser(BankUserDto bankUserDto) {
        var bankUser = bankUserMapper.toEntity(bankUserDto);
        bankUser.setActive(true);
        bankUserRepo.save(bankUser);
        return bankUserMapper.toDto(bankUser);
    }

    public List<BankUserDto> getAllBankUsers(String sort) {
        return bankUserRepo.findAllByIsActiveTrueAndBankIdIsActiveTrue(Sort.by(sort))
                .stream()
                .map(bankUserMapper::toDto)
                .toList();
    }

    public BankUserDto getBankUserById(long id){
        if (!bankUserRepo.existsById(id)) {
            return null;
        }
        var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        return bankUserMapper.toDto(bankUser);

    }

    public BankUserDto updateBankUser(long id, BankUserDto bankUserDto) {
        if (!bankUserRepo.existsById(id)) {
            return null;
        }
        var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        bankUserMapper.update(bankUserDto, bankUser);
        bankUser.setActive(true);
        bankUserRepo.save(bankUser);
        return bankUserMapper.toDto(bankUser);
    }

    public Boolean deleteBankUser(long id) {
        var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (bankUser == null) {
            return false;
        }
        bankUser.setActive(false);
        bankUserRepo.save(bankUser);
        return true;
    }

}
