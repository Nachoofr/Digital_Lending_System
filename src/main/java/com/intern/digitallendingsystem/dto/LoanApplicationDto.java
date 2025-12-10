package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationDto {
    private Long bankId;
    private Long loanProductId;
    private Long customerId;
    private Double requestedAmount;
    private Long customerBankAccountId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    private Date applicationDate;
    private Double approvedAmount;
    private String remarks;
}
