package com.intern.digitallendingsystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoanDisbursementDto {
    private Long loanApplicationId;
    private Date disbursementDate;
    private Double disbursementAmount;
    private String disbursementChannel;
}
