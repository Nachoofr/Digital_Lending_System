package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {
    List<Bank> findAllByIsActiveTrue();
//    Bank findByIdAndIsActiveTrue(long id);
    Optional<Bank> findByIdAndIsActiveTrue(long id);
}
