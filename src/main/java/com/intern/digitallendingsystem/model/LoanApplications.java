package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.LoanStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LoanApplications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long bankId;

    private long loanProductId;

    private double requestedAmount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private Date applicationDate;

    private double approvedAmount;

    private String remarks;


}
