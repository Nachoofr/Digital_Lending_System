package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepaymentRepo extends JpaRepository<LoanRepayment, Integer> {
}
