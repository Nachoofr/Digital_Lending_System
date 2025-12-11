package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.model.Bank;
import com.intern.digitallendingsystem.model.LoanDisbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface LoanDisbursementRepo extends JpaRepository<LoanDisbursement, Long> {
    List<LoanDisbursement> findAllByLoanApplicationIdBankId_Id(long id);

    LoanDisbursement findByLoanApplicationId_BankId_Id(long id);

    LoanDisbursement findByLoanApplicationId_CustomerId_Id(long id);
}
