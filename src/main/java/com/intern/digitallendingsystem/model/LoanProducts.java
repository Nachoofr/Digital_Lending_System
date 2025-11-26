package com.intern.digitallendingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoanProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long bankId;

    private String name;

    private String productCode;

    private long interestRate;

    private int tenureInMonths;

    private double minAmount;

    private double maxAmount;

    private double processingFeePercent;

    private boolean isActive;
}
