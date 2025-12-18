package com.intern.digitallendingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class LoanRepaymentDto {

    @NotNull
    private Long loanApplicationId;

    @NotNull
    private Date paymentDate;

    @NotNull
    private Double amountPaid;

    @NotNull
    private String paymentMethod;

    @NotNull
    private Long referenceNumber;
}
