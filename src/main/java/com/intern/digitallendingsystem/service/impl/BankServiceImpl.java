package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.mapper.BankMapper;
import com.intern.digitallendingsystem.model.Bank;
import com.intern.digitallendingsystem.repository.BankRepo;
import com.intern.digitallendingsystem.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private BankMapper bankMapper;
    private BankRepo bankRepo;

    public ResponseEntity<BankDto> createBank(BankDto bankDto) {
        var bank = bankMapper.toEntity(bankDto);
        bank.setActive(true);
        bankRepo.save(bank);
        return new ResponseEntity<>(bankMapper.toDto(bank), HttpStatus.CREATED);
    }

    public List<BankDto> getAllBanks() {
        return bankRepo.findAllByIsActiveTrue().stream()
                .map(bankMapper::toDto)
                .toList();
    }

    public ResponseEntity<BankDto> getBankById(long id) {
        Optional<Bank> bankOptional=bankRepo.findByIdAndIsActiveTrue(id);
        if(bankOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Bank bank=bankOptional.get();

        return new ResponseEntity<>(bankMapper.toDto(bank), HttpStatus.OK);
    }

    public ResponseEntity<BankDto> updateBank(long id, BankDto bankDto) {

        Optional<Bank> bankOptional=bankRepo.findByIdAndIsActiveTrue(id);
        if(bankOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Bank bank=bankOptional.get();
        bankMapper.update(bankDto, bank);
        bankRepo.save(bank);
        bank.setActive(true);
        return new ResponseEntity<>(bankMapper.toDto(bank), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBank(long id) {
        Optional<Bank> bankOptional=bankRepo.findByIdAndIsActiveTrue(id);
        if(bankOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Bank bank=bankOptional.get();
        bank.setActive(false);
        bankRepo.save(bank);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
