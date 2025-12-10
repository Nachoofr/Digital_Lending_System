package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepaymentRepo extends JpaRepository<LoanRepayment, Integer> {
    List<LoanRepayment> findAllByLoanApplicationIdId(long id);
}
