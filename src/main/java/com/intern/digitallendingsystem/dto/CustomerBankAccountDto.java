package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CustomerBankAccountDto {
    private long bankId;
    private long customerId;
    private double balance;

    @JsonIgnore
    private boolean isActive;
}
