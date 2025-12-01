package com.intern.digitallendingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {
    private long id;
    private String name;
    private int code;
    private String address;
    private boolean isActive;
}
