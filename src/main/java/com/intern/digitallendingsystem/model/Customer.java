package com.intern.digitallendingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bankId;

    @NotNull
    @Column(nullable = false)
    private boolean isActive;

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
    @Column(nullable = false, unique = true)
    private String phone;

    @NotNull
    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp = "^[A-za-z0-9]+$", message = "nationalId must contain only letters and numbers")
    private String nationalId;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$", message = "address must contain only letters")
    @Size(min = 3, message = "address must contain at least 3 characters")
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private Date createdAt;
}
