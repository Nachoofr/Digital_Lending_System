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
    private Long bankId;
    private String name;
    private String productCode;
    private Double interestRate;
    private Integer tenureInMonths;
    private Double minAmount;
    private Double maxAmount;
    private Double processingFeePercent;
    @JsonIgnore
    private Boolean isActive;

}
