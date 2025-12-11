package com.intern.digitallendingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportingBankApiDto {
    private long totalApplications;
    private long totalApprovals;
    private long totalRejected;
    private double totalDisbursedAmount;
    private double totalOutstandingAmount;

}