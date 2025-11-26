package com.intern.digitallendingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 5, message = "name must contain at least 5 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "name must contain only letters")
    @Column(nullable = false)
    private String fullName;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "phone number must have numbers only")
    @Column(nullable = false)
    private String phone;

    @NotNull
    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp = "^[A-za-z0-9]$+", message = "nationalId must contain only letters and numbers")
    private String nationalId;

    @NotBlank
    @Pattern(regexp = "^[a-za-z]+$", message = "address must contain only letters")
    @Size(min = 3, message = "address must contain at least 3 characters")
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private Date createdAt;
}
