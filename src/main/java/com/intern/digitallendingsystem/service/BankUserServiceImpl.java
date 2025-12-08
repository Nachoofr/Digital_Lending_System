package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankUserDto;
import com.intern.digitallendingsystem.mapper.BankUserMapper;
import com.intern.digitallendingsystem.repository.BankUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private BankUserMapper bankUserMapper;
    private BankUserRepo bankUserRepo;

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
        if (bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        return new ResponseEntity<>(bankUserMapper.toDto(bankUser), HttpStatus.OK);

    }

    public ResponseEntity<BankUserDto> updateBankUser(long id, BankUserDto bankUserDto) {
        if (bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        bankUserMapper.update(bankUserDto, bankUser);
        bankUser.setActive(true);
        bankUserRepo.save(bankUser);
        return new ResponseEntity<>(bankUserMapper.toDto(bankUser), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBankUser(long id) {
        var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (bankUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bankUser.setActive(false);
        bankUserRepo.save(bankUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
