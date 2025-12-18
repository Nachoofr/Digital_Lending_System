package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerBankAccountDto {

    private Long bankId;

    @NotNull
    private Long customerId;

    @NotNull
    private Double balance;

    @JsonIgnore
    private Boolean isActive;
}
