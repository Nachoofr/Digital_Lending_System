package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.enums.LoanStatus;
import com.intern.digitallendingsystem.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.image.PixelGrabber;
import java.util.Optional;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanApplication,Long> {
    Optional<LoanApplication> findByIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(Long id);
    int countByBankIdIdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(long id);
    int countByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrueAndStatus(long id, LoanStatus status);

    LoanApplication findByBankIdIdAndBankIdIsActiveTrueAndCustomerIdIsActiveTrue(long id);

    LoanApplication findByCustomerId_IdAndCustomerIdIsActiveTrueAndBankIdIsActiveTrue(long id);

    LoanApplication findByCustomerId_Id(long id);
}
