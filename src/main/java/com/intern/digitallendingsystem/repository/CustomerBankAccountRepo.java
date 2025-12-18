package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.Customer;
import com.intern.digitallendingsystem.model.CustomerBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerBankAccountRepo extends JpaRepository<CustomerBankAccount, Long> {
    List<CustomerBankAccount> findAllByCustomerIdIsActiveTrueAndIsActiveTrue();
    Optional<CustomerBankAccount> findByIdAndIsActiveTrueAndCustomerIdIsActiveTrue(Long id);
}
