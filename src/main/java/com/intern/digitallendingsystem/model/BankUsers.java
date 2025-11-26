package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
public class BankUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long bankId;

    private String fullName;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    private boolean isActive;


}
