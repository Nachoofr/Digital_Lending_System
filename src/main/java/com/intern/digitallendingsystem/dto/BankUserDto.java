package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {

    //todo
    //add required validations annotation based
    private Long bankId;
    private String fullName;
    private String email;
    private String role;
    @JsonIgnore
    private Boolean isActive;
}
