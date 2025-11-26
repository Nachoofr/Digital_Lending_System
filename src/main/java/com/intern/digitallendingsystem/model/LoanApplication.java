package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long bankId;


    private long loanProductId;

    @NotNull
    @Column(nullable = false)
    private double requestedAmount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @NotNull
    @Column(nullable = false)
    private Date applicationDate;

    @NotNull
    @Column(nullable = false)
    private double approvedAmount;

    private String remarks;


}
