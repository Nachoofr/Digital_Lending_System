package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {

    //todo
    //add required validations annotation based
    @NotNull
    private Long bankId;

    @NotBlank
    @Size(min = 2, message = "name must contain at least 2 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "name must contain only letters")
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private String role;

    @JsonIgnore
    private Boolean isActive;
}
