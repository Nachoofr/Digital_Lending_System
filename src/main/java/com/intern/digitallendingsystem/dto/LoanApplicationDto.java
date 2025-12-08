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
    private long bankId;
    private long loanProductId;
    private long customerId;
    private double requestedAmount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    private Date applicationDate;
    private double approvedAmount;
    private String remarks;
}
