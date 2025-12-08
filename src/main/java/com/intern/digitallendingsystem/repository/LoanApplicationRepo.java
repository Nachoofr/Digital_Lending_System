package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.LoanApplication;
import com.intern.digitallendingsystem.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanApplication,Long> {
    LoanApplication findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(Long id);
}
