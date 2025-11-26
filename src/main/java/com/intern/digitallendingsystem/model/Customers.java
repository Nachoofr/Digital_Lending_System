package com.intern.digitallendingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

import java.util.Date;

@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    @Email
    private String email;

    private String phone;

    private Date dateOfBirth;

    private String nationalId;

    private String address;

    private Date createdAt;
}
