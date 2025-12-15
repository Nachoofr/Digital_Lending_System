package com.intern.digitallendingsystem.model;

import com.intern.digitallendingsystem.enums.DisbursementChannel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class LoanDisbursement {//once approved loan can be disbursed

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "loanApplication_id", nullable = false)
    LoanApplication loanApplicationId;

    @NotNull
    @Column(nullable = false)
    private Date disbursementDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate maturityDate;

    @NotNull
    @Column(nullable = false)
    private double disbursementAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DisbursementChannel disbursementChannel;
}
