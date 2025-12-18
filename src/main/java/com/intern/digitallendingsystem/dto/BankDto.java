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
public class BankDto {

    //todo
    //add required validations annotation based
//    private long id;
    @NotBlank
    private String name;

    @NotNull
    @Min(100)
    @Max(999)
    private Integer code;

    @NotBlank
    @Size(min = 3, message = "address must contain at least 3 characters")
    @Pattern(regexp = "^[A-za-z]+$", message = "address must contain only letters")
    private String address;

    @JsonIgnore
    private Boolean isActive;

    @NotNull
    @Column(nullable = false)
    private Date createdAt;
}
