package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.LoanApplicationDto;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.mapper.LoanApplicationMapper;
import com.intern.digitallendingsystem.model.Customer;
import com.intern.digitallendingsystem.model.LoanApplication;
import com.intern.digitallendingsystem.repository.CustomerRepo;
import com.intern.digitallendingsystem.repository.LoanApplicationRepo;
import com.intern.digitallendingsystem.service.LoanApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {
    LoanApplicationRepo loanApplicationRepo;
    LoanApplicationMapper loanApplicationMapper;
    CustomerRepo customerRepo;

    //todo
    //implement proper logging using SL4J
    public ResponseEntity<LoanApplicationDto> createLoanApplication(LoanApplicationDto LoanApplicationDto){
        var loanApplication = loanApplicationMapper.toEntity(LoanApplicationDto);
        Optional<Customer> optionalCustomer =customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(LoanApplicationDto.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = optionalCustomer.get();
//        var account = customerRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(LoanApplicationDto.getCustomerBankAccountId());
        loanApplication.setStatus(LoanStatus.PENDING);
        loanApplication.setBankId(customer.getBankId());
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
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (optionalLoanApplication.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanApplication loanApplication = optionalLoanApplication.get();
        return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.OK);
    }

    public ResponseEntity<LoanApplicationDto> approveLoanApplication(long id){
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (optionalLoanApplication.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanApplication loanApplication = optionalLoanApplication.get();
        var loanProduct = loanApplication.getLoanProductId();

        if (loanApplication.getRequestedAmount() <= loanProduct.getMaxAmount()){
            loanApplication.setStatus(LoanStatus.APPROVED);
            loanApplication.setApprovedAmount(loanApplication.getRequestedAmount());
            loanApplicationRepo.save(loanApplication);
            return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.OK);
        }
        return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<LoanApplicationDto> rejectLoanApplication(long id){
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (optionalLoanApplication.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanApplication loanApplication = optionalLoanApplication.get();
        loanApplication.setStatus(LoanStatus.REJECTED);
        loanApplicationRepo.save(loanApplication);
        return new ResponseEntity<>(loanApplicationMapper.toDto(loanApplication), HttpStatus.OK);
    }
}
