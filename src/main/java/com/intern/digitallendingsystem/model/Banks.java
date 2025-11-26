package com.intern.digitallendingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class Banks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank
    @Size(min = 5, message = "name must contain at least 5 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "name must contain only letters")
    @Column(nullable = false)
    private String name;

    @NotNull
    @Size(min = 3, max =3, message = "code must be of exactly 3 characters")
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
