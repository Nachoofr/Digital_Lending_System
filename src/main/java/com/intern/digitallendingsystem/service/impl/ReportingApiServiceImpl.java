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
import com.intern.digitallendingsystem.util.ReportingApiUtil;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class ReportingApiServiceImpl implements ReportingApiService {
    ReportingApiUtil reportingApiUtil;
    LoanApplicationRepo loanApplicationRepo;

    public ReportingBankApiDto bankLoanSummary(long id) {
        long totalApplication = loanApplicationRepo.countByBankIdIdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id);
        long totalApproved = loanApplicationRepo.countByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrueAndStatus(id, LoanStatus.APPROVED);
        long totalRejected = loanApplicationRepo.countByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrueAndStatus(id, LoanStatus.REJECTED);
        double totalDisbursedAmount = reportingApiUtil.totalDisbursedAmountByBankId(id);
        double totalOutstandingAmount = reportingApiUtil.calculateTotalOutstandingAmountByBank(id);


        return new ReportingBankApiDto(totalApplication, totalApproved, totalRejected, totalDisbursedAmount, totalOutstandingAmount);
    }

    public ReportingCustomerApiDto customerLoanSummary(long id) {
        LoanStatus status = loanApplicationRepo.findByCustomerId_IdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id).getStatus();
        double approvedAmount = loanApplicationRepo.findByCustomerId_IdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(id).getApprovedAmount();
        double totalRepaidAmount = reportingApiUtil.totalRepaidAmountByCustomer(id);
        double totalOutstandingAmount = reportingApiUtil.calculateTotalOutstandingAmountByCustomer(id);

        return new ReportingCustomerApiDto(status, approvedAmount, totalRepaidAmount, totalOutstandingAmount);
    }




}
