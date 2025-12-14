package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanProductDto {

    //todo
    //add required validations annotation based
    private long bankId;
    private String name;
    private String productCode;
    private double interestRate;
    private int tenureInMonths;
    private double minAmount;
    private double maxAmount;
    private double processingFeePercent;
    @JsonIgnore
    private Boolean isActive;

}
