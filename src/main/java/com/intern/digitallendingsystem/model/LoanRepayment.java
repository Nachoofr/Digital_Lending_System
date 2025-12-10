package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.LoanRepaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class LoanRepayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "loanApplication_id", nullable = false)
    LoanApplication loanApplicationId;

    @NotNull
    @Column(nullable = false)
    private Date paymentDate;

    @NotNull
    @Column(nullable = false)
    private double amountPaid;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanRepaymentMethod paymentMethod;

    @NotNull
    @Column(nullable = false)
    private long referenceNumber;
}
