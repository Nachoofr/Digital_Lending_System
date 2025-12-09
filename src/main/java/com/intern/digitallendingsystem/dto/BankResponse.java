package com.intern.digitallendingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {
    private Long id;
    private String name;
    private Integer code;
    private String address;
    private Boolean isActive;
}
