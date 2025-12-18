package com.intern.digitallendingsystem.util;

import com.intern.digitallendingsystem.model.LoanApplication;
import com.intern.digitallendingsystem.model.LoanRepayment;
import com.intern.digitallendingsystem.repository.LoanApplicationRepo;
import com.intern.digitallendingsystem.repository.LoanDisbursementRepo;
import com.intern.digitallendingsystem.repository.LoanRepaymentRepo;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReportingApiUtil {
    LoanApplicationRepo loanApplicationRepo;
    LoanDisbursementRepo loanDisbursementRepo;
    LoanRepaymentRepo loanRepaymentRepo;

    public double totalDisbursedAmountByBankId(long id) {
        var loanDisbursement = loanDisbursementRepo.findByLoanApplicationId_BankId_Id(id);
        var totalDisbursedAmount = loanDisbursement.getDisbursementAmount();
        return totalDisbursedAmount;
    }

    public double totalRepaidAmountByBank(long id){
        return loanRepaymentRepo.findAllByLoanApplicationId_BankId_Id(id).stream()
                .mapToDouble( LoanRepayment:: getAmountPaid)
                .sum();
    }

    public double totalRepaidAmountByCustomer(long id){
        return loanRepaymentRepo.findAllByLoanApplicationId_CustomerId_Id(id).stream()
                .mapToDouble( LoanRepayment :: getAmountPaid)
                .sum();
    }

    public double calculateTotalOutstandingAmountByBank(long id){
        double totalDisbursedAmount = totalDisbursedAmountByBankId(id);
        double totalRepaymentAmount = totalRepaidAmountByBank(id);
        LoanApplication loanApplication = loanApplicationRepo.findByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(id);
        double interest = (loanApplication.getLoanProductId().getInterestRate()/100)* totalDisbursedAmount;
        return totalDisbursedAmount + interest - totalRepaymentAmount;
    }

    public double calculateTotalOutstandingAmountByCustomer(long id){
        double totalDisbursedAmount = loanDisbursementRepo.findByLoanApplicationId_CustomerId_Id(id).getDisbursementAmount();
        double totalRepaymentAmount = totalRepaidAmountByCustomer(id);
        var loanApplication = loanApplicationRepo.findByCustomerId_IdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id);
        double interest = (loanApplication.getLoanProductId().getInterestRate()/100)* totalDisbursedAmount;
        return totalDisbursedAmount + interest - totalRepaymentAmount;
    }
}
