package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.LoanRepaymentMethod;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LoanRepayments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long loanApplicationId;

    private Date paymentDate;

    private double amountPaid;

    @Enumerated(EnumType.STRING)
    private LoanRepaymentMethod paymentMethod;

    private long referenceNumber;
}
