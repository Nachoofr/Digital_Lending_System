package com.intern.digitallendingsystem.dto;

import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.model.LoanApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.weaver.ast.Var;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReportingCustomerApiDto {
    private LoanStatus status;
    private double approvedAmount;
    private double totalRepaidAmount;
    private double totalOutstandingAmount;
}
