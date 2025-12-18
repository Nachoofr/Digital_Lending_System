package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationDto {
    //todo
    //add required validations annotation based
    private Long bankId;

    @NotNull
    private Long loanProductId;

    @NotNull
    private Long customerId;

    @NotNull
    private Double requestedAmount;

    @NotNull
    private Long customerBankAccountId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    @NotNull
    private Date applicationDate;

    @NotNull
    private Double approvedAmount;

    private String remarks;
}
