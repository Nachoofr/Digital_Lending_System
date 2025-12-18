package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.mapper.LoanRepaymentMapper;
import com.intern.digitallendingsystem.model.LoanApplication;
import com.intern.digitallendingsystem.model.LoanRepayment;
import com.intern.digitallendingsystem.repository.LoanApplicationRepo;
import com.intern.digitallendingsystem.repository.LoanRepaymentRepo;
import com.intern.digitallendingsystem.service.LoanRepaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanRepaymentServiceImpl implements LoanRepaymentService {
    LoanRepaymentRepo loanRepaymentRepo;
    LoanRepaymentMapper loanRepaymentMapper;
    LoanApplicationRepo loanApplicationRepo;

    public double totalRepaidAmount(long id){
        return loanRepaymentRepo.findAllByLoanApplicationIdId(id).stream()
                .mapToDouble( LoanRepayment :: getAmountPaid)
                .sum();
    }

    public ResponseEntity<LoanRepaymentDto> makeRepayment(long id, LoanRepaymentDto loanRepaymentDto) {
        double totalDisbursementAmount = 0.0;
        double totalRepaidAmount = 0.0;
        double totalOutstandingAmount = 0.0;
        double neededAmount = 0.0;
        double interest = 0.0;

        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (optionalLoanApplication.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanApplication loanApplication = optionalLoanApplication.get();

        totalDisbursementAmount= loanApplication.getApprovedAmount();
        totalRepaidAmount = totalRepaidAmount(id);
        interest = (loanApplication.getLoanProductId().getInterestRate()/100)* totalDisbursementAmount;
        neededAmount = totalDisbursementAmount + interest;
        totalOutstandingAmount = totalDisbursementAmount + interest - totalRepaidAmount;

        System.out.println("Total Disbursement Amount: " + totalDisbursementAmount);
        System.out.println("Total Repaid Amount: " + totalRepaidAmount);
        System.out.println("Interest: " + interest);
        System.out.println("Needed Amount: " + neededAmount);
        System.out.println("Total Outstanding Amount: " + totalOutstandingAmount);
        System.out.println(loanRepaymentDto.getAmountPaid() - totalOutstandingAmount);

        if (totalRepaidAmount < neededAmount && loanApplication.getStatus() != LoanStatus.CLOSED && (totalOutstandingAmount - loanRepaymentDto.getAmountPaid()) >=0) {
            System.out.println("before saving repayment");
            var loanRepayment = loanRepaymentMapper.toEntity(loanRepaymentDto);
            loanRepayment.setLoanApplicationId(loanApplication);
            loanRepaymentRepo.save(loanRepayment);

            totalRepaidAmount = totalRepaidAmount(id);
            totalOutstandingAmount = totalDisbursementAmount + interest - totalRepaidAmount;

            if (totalOutstandingAmount<= 0){
                System.out.println("Closing loan application");
                loanApplication.setStatus(LoanStatus.CLOSED);
                loanApplicationRepo.save(loanApplication);
            }

            return new ResponseEntity<>(loanRepaymentMapper.toDto(loanRepayment), HttpStatus.OK);

        }
        System.out.println("Repayment exceeds disbursement  or outstanding amount or The loan is already closed");
        System.out.println("Total Disbursement Amount: " + totalDisbursementAmount);
        System.out.println("Total Outstanding Amount: " + totalOutstandingAmount);
        System.out.println("Amount being deposited: " + loanRepaymentDto.getAmountPaid());

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<LoanRepaymentDto>> getRepayments(long id){
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (optionalLoanApplication.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanApplication loanApplication = optionalLoanApplication.get();
        System.out.println(loanApplication);


        var repayments = loanRepaymentRepo.findAllByLoanApplicationIdId(id).stream()
                .map(loanRepaymentMapper::toDto)
                .toList();
        return new ResponseEntity<>(repayments, HttpStatus.OK);
    }
}
