package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.LoanDisbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDisbursementRepo extends JpaRepository<LoanDisbursement, Long> {
}
