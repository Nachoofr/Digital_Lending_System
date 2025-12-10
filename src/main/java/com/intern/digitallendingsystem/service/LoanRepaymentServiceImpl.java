package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanRepaymentDto;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.mapper.LoanRepaymentMapper;
import com.intern.digitallendingsystem.model.LoanRepayment;
import com.intern.digitallendingsystem.repository.LoanApplicationRepo;
import com.intern.digitallendingsystem.repository.LoanRepaymentRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanRepaymentServiceImpl implements LoanRepaymentService {
    LoanRepaymentRepo loanRepaymentRepo;
    LoanRepaymentMapper loanRepaymentMapper;
    LoanApplicationRepo loanApplicationRepo;

    double totalRepaidAmount(long id){
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

        var loanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (loanApplication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        totalDisbursementAmount= loanApplication.getApprovedAmount();
        totalRepaidAmount = totalRepaidAmount(id);
        interest = (loanApplication.getLoanProductId().getInterestRate()/100)* totalDisbursementAmount;
        neededAmount = totalDisbursementAmount + interest;
        totalOutstandingAmount = totalDisbursementAmount + interest - totalRepaidAmount;

        if (totalRepaidAmount < neededAmount && loanApplication.getStatus() != LoanStatus.CLOSED && (loanRepaymentDto.getAmountPaid() - totalOutstandingAmount) >=0) {
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
        var loanApplication = loanApplicationRepo.findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        if (loanApplication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var repayments = loanRepaymentRepo.findAllByLoanApplicationIdId(id).stream()
                .map(loanRepaymentMapper::toDto)
                .toList();
        return new ResponseEntity<>(repayments, HttpStatus.OK);
    }
}
