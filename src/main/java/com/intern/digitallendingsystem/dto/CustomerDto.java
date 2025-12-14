package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    //todo
    //add required validations annotation based
    private long bankId;
    private String fullName;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private String nationalId;
    private String address;
    private Date createdAt;

    @JsonIgnore
    private Boolean isActive;
}
