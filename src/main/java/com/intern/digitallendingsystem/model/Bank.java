package com.intern.digitallendingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank
    @Size(min = 3, message = "name must contain at least 3 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "name must contain only letters")
    @Column(nullable = false)
    private String name;

    @NotNull
    @Min(100)
    @Max(999)
//    @Size(min = 3, max =3, message = "code must be of exactly 3 characters")
    @Column(nullable = false)
    private int code;

    @NotBlank
    @Size(min = 3, message = "address must contain at least 3 characters")
    @Pattern(regexp = "^[A-za-z]+$", message = "address must contain only letters")
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private boolean isActive;

    @NotNull
    @Column(nullable = false)
    private Date createdAt;
}
