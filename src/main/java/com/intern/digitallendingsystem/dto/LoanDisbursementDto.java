package com.intern.digitallendingsystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class LoanDisbursementDto {
    private Long loanApplicationId;
    private Date disbursementDate;
    private LocalDate maturityDate;
    private Double disbursementAmount;
    private String disbursementChannel;
}
