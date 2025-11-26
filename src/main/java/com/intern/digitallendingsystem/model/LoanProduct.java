package com.intern.digitallendingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;



    private long bankId;

    @NotBlank
    @Size(min = 3, message = "name must contain at least 3 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(min = 3, max = 5, message = "product code must contain at least 3 characters")
    @Column(nullable = false)
    private String productCode;

    @NotNull
    @Column(nullable = false)
    private long interestRate;

    @NotNull
    @Column(nullable = false)
    private int tenureInMonths;

    @NotNull
    @Column(nullable = false)
    private double minAmount;

    @NotNull
    @Column(nullable = false)
    private double maxAmount;

    @NotNull
    @Column(nullable = false)
    private double processingFeePercent;

    @NotNull
    @Column(nullable = false)
    private boolean isActive;
}
