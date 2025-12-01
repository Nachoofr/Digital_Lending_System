package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.BankDto;
import com.intern.digitallendingsystem.mapper.BankMapper;
import com.intern.digitallendingsystem.repository.BankRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements  BankService {
    private BankMapper bankMapper;
    private BankRepo bankRepo;

    public BankDto createBank(BankDto bankDto) {
        var bank = bankMapper.toEntity(bankDto);
        bank.setActive(true);
        bankRepo.save(bank);

        return bankMapper.toDto(bank);
    }

    public List<BankDto> getAllBanks() {
        return bankRepo.findAllByIsActiveTrue().stream()
                .map(bankMapper::toDto)
                .toList();
    }

    public BankDto getBankById(long id) {
        if (!bankRepo.existsById(id)) {
            return null;
        }
        var bank = bankRepo.findByIdAndIsActiveTrue(id);
        return bankMapper.toDto(bank);
    }

    public BankDto updateBank(long id, BankDto bankDto) {
        var bank = bankRepo.findById(id).orElse(null);
        bankMapper.update(bankDto, bank);
        bankRepo.save(bank);
        bank.setActive(true);
        return bankMapper.toDto(bank);
    }

    public boolean deleteBank(long id) {
        var bank = bankRepo.findById(id).orElse(null);
        if (bank == null) {
            return false;
        }
        bank.setActive(false);
        bankRepo.save(bank);
        return true;
    }


}
