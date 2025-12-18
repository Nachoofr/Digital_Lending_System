package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findAllByIsActiveTrueAndBankIdIsActiveTrue(Sort sort);
    Optional<Customer> findByIdAndIsActiveTrueAndBankIdIsActiveTrue(long id);
}
