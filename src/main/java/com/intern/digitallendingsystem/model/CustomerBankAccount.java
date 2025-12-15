package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class CustomerBankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "bank_id", nullable = false)
    Bank bankId;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customerId;

    @NotNull
    @Column(nullable = false)
    private double balance;

    @NotNull
    @Column(nullable = false)
    private boolean isActive;

}
