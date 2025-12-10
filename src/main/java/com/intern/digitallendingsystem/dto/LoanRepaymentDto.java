package com.intern.digitallendingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoanRepaymentDto {
    private Long loanApplicationId;
    private Date paymentDate;
    private Double amountPaid;
    private String paymentMethod;
    private Long referenceNumber;
}
