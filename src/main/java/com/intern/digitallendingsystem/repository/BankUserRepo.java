package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.model.BankUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankUserRepo extends JpaRepository<BankUser, Long> {
//    BankUser findByIdAndIsActiveTrueAndBankIdIsActiveTrue(long id);
    Optional<BankUser> findByIdAndIsActiveTrueAndBankIdIsActiveTrue(long id);
    List<BankUser> findAllByIsActiveTrueAndBankIdIsActiveTrue(Sort sort);
}

