package com.intern.digitallendingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class LoanDisbursementDto {

    @NotNull
    private Long loanApplicationId;

    @NotNull
    private Date disbursementDate;

    @NotNull
    private LocalDate maturityDate;

    @NotNull
    private Double disbursementAmount;

    @NotNull
    private String disbursementChannel;
}
