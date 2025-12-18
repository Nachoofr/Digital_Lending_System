package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.LoanDisbursementDto;
import com.intern.digitallendingsystem.enums.DisbursementChannel;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.mapper.LoanDisbursementMapper;
import com.intern.digitallendingsystem.model.CustomerBankAccount;
import com.intern.digitallendingsystem.model.LoanApplication;
import com.intern.digitallendingsystem.repository.CustomerBankAccountRepo;
import com.intern.digitallendingsystem.repository.LoanApplicationRepo;
import com.intern.digitallendingsystem.repository.LoanDisbursementRepo;
import com.intern.digitallendingsystem.service.LoanDisbursementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanDisbursementServiceImpl implements LoanDisbursementService {
    LoanDisbursementMapper loanDisbursementMapper;
    LoanDisbursementRepo loanDisbursementRepo;
    LoanApplicationRepo loanApplicationRepo;
    CustomerBankAccountRepo customerBankAccountRepo;


    public ResponseEntity<LoanDisbursementDto> disburse(long id, LoanDisbursementDto loanDisbursementDto) {
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if(optionalLoanApplication.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanApplication loanApplication = optionalLoanApplication.get();

        Optional<CustomerBankAccount> optionalCustomerBankAccount = customerBankAccountRepo.findByIdAndIsActiveTrueAndCustomerIdIsActiveTrue(loanApplication.getCustomerBankAccountId().getId());
        if(optionalCustomerBankAccount.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CustomerBankAccount customerBankAccount = optionalCustomerBankAccount.get();

        if (loanApplication.getStatus() == LoanStatus.APPROVED) {
            var loanDisbursement = loanDisbursementMapper.toEntity(loanDisbursementDto);

            loanDisbursement.setLoanApplicationId(loanApplication);
            loanDisbursement.setDisbursementAmount(loanApplication.getApprovedAmount());

            LocalDate localDate = loanDisbursement.getDisbursementDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate maturityDate = localDate.plusDays(30);
            loanDisbursement.setMaturityDate(maturityDate);
            var savedDisbursement = loanDisbursementRepo.save(loanDisbursement);

            loanApplication.setStatus(LoanStatus.DISBURSED);
            loanApplicationRepo.save(loanApplication);
            if(loanDisbursement.getDisbursementChannel() == DisbursementChannel.BANK_TRANSFER) {
                var newBalance = customerBankAccount.getBalance() + loanDisbursement.getDisbursementAmount();
                customerBankAccount.setBalance(newBalance);
                customerBankAccountRepo.save(customerBankAccount);
                return new ResponseEntity<>(loanDisbursementMapper.toDto(savedDisbursement), HttpStatus.OK);
            }

            return new ResponseEntity<>(loanDisbursementMapper.toDto(savedDisbursement), HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    }

