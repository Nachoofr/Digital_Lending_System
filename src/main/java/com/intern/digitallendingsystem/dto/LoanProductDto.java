package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanProductDto {

    //todo
    //add required validations annotation based

    @NotNull
    private Long bankId;

    @NotBlank
    @Size(min = 3, message = "name must contain at least 3 characters")
    private String name;

    @NotBlank
    @Size(min = 3, max = 5, message = "product code must contain at least 3 characters")
    private String productCode;

    @NotNull
    private Double interestRate;

    @NotNull
    private Integer tenureInMonths;

    @NotNull
    private Double minAmount;

    @NotNull
    private Double maxAmount;

    @NotNull
    private Double processingFeePercent;

    @JsonIgnore
    private Boolean isActive;

}
