package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.mapper.BankMapper;
import com.intern.digitallendingsystem.repository.BankRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements  BankService {
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
        if (bankRepo.findByIdAndIsActiveTrue(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var bank = bankRepo.findByIdAndIsActiveTrue(id);
        return new ResponseEntity<>(bankMapper.toDto(bank), HttpStatus.OK);
    }

    public ResponseEntity<BankDto> updateBank(long id, BankDto bankDto) {
        if (!bankRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var bank = bankRepo.findByIdAndIsActiveTrue(id);
        bankMapper.update(bankDto, bank);
        bankRepo.save(bank);
        bank.setActive(true);
        return new ResponseEntity<>(bankMapper.toDto(bank), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBank(long id) {
        var bank = bankRepo.findByIdAndIsActiveTrue(id);
        if (bank == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bank.setActive(false);
        bankRepo.save(bank);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
