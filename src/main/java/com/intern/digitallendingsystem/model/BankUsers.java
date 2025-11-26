package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class BankUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private long bankId;

    @NotBlank
    @Size(min = 2, message = "name must contain at least 2 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "name must contain only letters")
    @Column(nullable = false)
    private String fullName;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoles role;

    @NotNull
    @Column(nullable = false)
    private boolean isActive;


}
