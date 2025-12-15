package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CustomerBankAccountDto {
    private Long bankId;
    private Long customerId;
    private Double balance;

    @JsonIgnore
    private Boolean isActive;
}
