package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.DisbursementChannel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class LoanDisbursements {//once approved loan can be disbursed

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long loanApplicationId;

    @NotNull
    @Column(nullable = false)
    private Date disbursementDate;

    @NotNull
    @Column(nullable = false)
    private double disbursementAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DisbursementChannel disbursementChannel;
}
