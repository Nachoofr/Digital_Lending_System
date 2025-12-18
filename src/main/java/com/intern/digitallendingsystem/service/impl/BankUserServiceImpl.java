package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.BankUserDto;
import com.intern.digitallendingsystem.mapper.BankUserMapper;
import com.intern.digitallendingsystem.model.BankUser;
import com.intern.digitallendingsystem.repository.BankUserRepo;
import com.intern.digitallendingsystem.service.BankUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private final BankUserMapper bankUserMapper;
    private final BankUserRepo bankUserRepo;

    public ResponseEntity<BankUserDto> createBankUser(BankUserDto bankUserDto) {
        var bankUser = bankUserMapper.toEntity(bankUserDto);
        bankUser.setActive(true);
        bankUserRepo.save(bankUser);
        return new ResponseEntity<>(bankUserMapper.toDto(bankUser), HttpStatus.CREATED);
    }

    public List<BankUserDto> getAllBankUsers(String sort) {
        Sort sorting = (sort == null || sort.isBlank()) ? Sort.unsorted() : Sort.by(sort);
        return bankUserRepo.findAllByIsActiveTrueAndBankIdIsActiveTrue(sorting)
                .stream()
                .map(bankUserMapper::toDto)
                .toList();
    }

    public ResponseEntity<BankUserDto> getBankUserById(long id){
        Optional<BankUser> optionalBankUser = bankUserRepo.findById(id);
        if (optionalBankUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BankUser bankUser = optionalBankUser.get();
        return new ResponseEntity<>(bankUserMapper.toDto(bankUser), HttpStatus.OK);

    }

    public ResponseEntity<BankUserDto> updateBankUser(long id, BankUserDto bankUserDto) {
        Optional<BankUser> optionalBankUser = bankUserRepo.findById(id);
        if (optionalBankUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BankUser bankUser = optionalBankUser.get();
        bankUserMapper.update(bankUserDto, bankUser);
        bankUser.setActive(true);
        bankUserRepo.save(bankUser);
        return new ResponseEntity<>(bankUserMapper.toDto(bankUser), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBankUser(long id) {
        Optional<BankUser> optionalBankUser = bankUserRepo.findById(id);
        if (optionalBankUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BankUser bankUser = optionalBankUser.get();
        bankUser.setActive(false);
        bankUserRepo.save(bankUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
