package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "bank_id", nullable = false)
    Bank bankId;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customerId;

    @OneToOne
    @JoinColumn(name = "loanProduct_id", nullable = false)
    LoanProduct loanProductId;

    @OneToOne
    @JoinColumn(name = "customerBankAccount_id")
    private CustomerBankAccount customerBankAccount;

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
