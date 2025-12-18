package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.ReportingBankApiDto;
import com.intern.digitallendingsystem.dto.ReportingCustomerApiDto;
import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.model.LoanApplication;
import com.intern.digitallendingsystem.model.LoanRepayment;
import com.intern.digitallendingsystem.repository.LoanApplicationRepo;
import com.intern.digitallendingsystem.repository.LoanDisbursementRepo;
import com.intern.digitallendingsystem.repository.LoanRepaymentRepo;
import com.intern.digitallendingsystem.service.ReportingApiService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class ReportingApiServiceImpl implements ReportingApiService {
    LoanApplicationRepo loanApplicationRepo;
    LoanDisbursementRepo loanDisbursementRepo;
    LoanRepaymentRepo loanRepaymentRepo;

    double totalDisbursedAmountByBankId(long id) {
        var loanDisbursement = loanDisbursementRepo.findByLoanApplicationId_BankId_Id(id);
        var totalDisbursedAmount = loanDisbursement.getDisbursementAmount();
        return totalDisbursedAmount;
    }

    public double totalRepaidAmountByBank(long id){
        return loanRepaymentRepo.findAllByLoanApplicationId_BankId_Id(id).stream()
                .mapToDouble( LoanRepayment :: getAmountPaid)
                .sum();
    }

    public double totalRepaidAmountByCustomer(long id){
        return loanRepaymentRepo.findAllByLoanApplicationId_CustomerId_Id(id).stream()
                .mapToDouble( LoanRepayment :: getAmountPaid)
                .sum();
    }

    double calculateTotalOutstandingAmountByBank(long id){
        double totalDisbursedAmount = totalDisbursedAmountByBankId(id);
        double totalRepaymentAmount = totalRepaidAmountByBank(id);
        LoanApplication loanApplication = loanApplicationRepo.findByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        double interest = (loanApplication.getLoanProductId().getInterestRate()/100)* totalDisbursedAmount;
        return totalDisbursedAmount + interest - totalRepaymentAmount;
    }

    double calculateTotalOutstandingAmountByCustomer(long id){
        double totalDisbursedAmount = loanDisbursementRepo.findByLoanApplicationId_CustomerId_Id(id).getDisbursementAmount();
        double totalRepaymentAmount = totalRepaidAmountByCustomer(id);
        var loanApplication = loanApplicationRepo.findByCustomerId_IdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id);
        double interest = (loanApplication.getLoanProductId().getInterestRate()/100)* totalDisbursedAmount;
        return totalDisbursedAmount + interest - totalRepaymentAmount;
    }

    public ReportingBankApiDto bankLoanSummary(long id) {
        long totalApplication = loanApplicationRepo.countByBankIdIdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id);
        long totalApproved = loanApplicationRepo.countByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrueAndStatus(id, LoanStatus.APPROVED);
        long totalRejected = loanApplicationRepo.countByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrueAndStatus(id, LoanStatus.REJECTED);
        double totalDisbursedAmount = totalDisbursedAmountByBankId(id);
        double totalOutstandingAmount = calculateTotalOutstandingAmountByBank(id);


        return new ReportingBankApiDto(totalApplication, totalApproved, totalRejected, totalDisbursedAmount, totalOutstandingAmount);
    }

    public ReportingCustomerApiDto customerLoanSummary(long id) {
        LoanStatus status = loanApplicationRepo.findByCustomerId_IdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id).getStatus();
        double approvedAmount = loanApplicationRepo.findByCustomerId_IdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id).getApprovedAmount();
        double totalRepaidAmount = totalRepaidAmountByCustomer(id);
        double totalOutstandingAmount = calculateTotalOutstandingAmountByCustomer(id);

        return new ReportingCustomerApiDto(status, approvedAmount, totalRepaidAmount, totalOutstandingAmount);
    }




}
