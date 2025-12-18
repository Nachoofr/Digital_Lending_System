package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
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
    @NotNull
    private long bankId;

    @NotBlank
    @Size(min = 5, message = "name must contain at least 5 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "name must contain only letters")
    @Column(nullable = false)
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "phone number must have numbers only")
    private String phone;

    @NotNull
    private Date dateOfBirth;

    @NotBlank
    @Pattern(regexp = "^[A-za-z0-9]+$", message = "nationalId must contain only letters and numbers")
    private String nationalId;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$", message = "address must contain only letters")
    @Size(min = 3, message = "address must contain at least 3 characters")
    private String address;

    @NotNull
    private Date createdAt;

    @JsonIgnore
    private Boolean isActive;
}
