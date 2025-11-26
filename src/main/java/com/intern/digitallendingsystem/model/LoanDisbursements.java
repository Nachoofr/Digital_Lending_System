package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.DisbursementChannel;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LoanDisbursements {//once approved loan can be disbursed

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long loanApplicationId;

    private Date disbursementDate;

    private double disbursementAmount;

    @Enumerated(EnumType.STRING)
    private DisbursementChannel disbursementChannel;
}
