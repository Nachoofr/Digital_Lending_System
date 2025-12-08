package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanApplicationDto;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.mapper.LoanApplicationMapper;
import com.intern.digitallendingsystem.repository.LoanApplicationRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {
    LoanApplicationRepo loanApplicationRepo;
    LoanApplicationMapper loanApplicationMapper;

    //todo
    //implement proper logging using SL4J
    public ResponseEntity<LoanApplicationDto> createLoanApplication(LoanApplicationDto LoanApplicationDto){
        var loanApplication = loanApplicationMapper.toEntity(LoanApplicationDto);
        loanApplication.setStatus(LoanStatus.PENDING);
        loanApplicationRepo.save(loanApplication);
        return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.OK);
    }

    public List<LoanApplicationDto> getAllLoanApplication(String status, long bankId, long customerId){
        var loanApplication = loanApplicationRepo.findAll();
        LoanStatus loanStatus = LoanStatus.valueOf(status.trim().toUpperCase());//converted string to enum
        if (status != null && !status.isBlank()){
            loanApplication = loanApplication.stream()
                    .filter(loan -> loan.getStatus().equals(loanStatus))
                    .toList();
        }

        if (bankId != 0){
            loanApplication = loanApplication.stream()
                    .filter(loan -> loan.getBankId().getId() == bankId)
                    .toList();
        }

        if (customerId != 0){
            loanApplication = loanApplication.stream()
                    .filter(loan -> loan.getCustomerId().getId() == customerId)
                    .toList();
        }

        return loanApplication.stream()
                .map(loanApplicationMapper::toDto)
                .toList();

    }

    public ResponseEntity<LoanApplicationDto> getLoanApplicationById(long id){
        var loanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (loanApplication == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.OK);
    }

    public ResponseEntity<LoanApplicationDto> approveLoanApplication(long id){
        var loanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        var loanProduct = loanApplication.getLoanProductId();
        if (loanApplication == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (loanApplication.getRequestedAmount() <= loanProduct.getMaxAmount()){
            loanApplication.setStatus(LoanStatus.APPROVED);
            loanApplication.setApprovedAmount(loanApplication.getRequestedAmount());
            loanApplicationRepo.save(loanApplication);
            return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.OK);
        }
        return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<LoanApplicationDto> rejectLoanApplication(long id){
        var loanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (loanApplication == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        loanApplication.setStatus(LoanStatus.REJECTED);
        loanApplicationRepo.save(loanApplication);
        return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.OK);
    }
}
