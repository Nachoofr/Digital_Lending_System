package com.intern.digitallendingsystem.repository;

import com.intern.digitallendingsystem.dto.LoanProductDto;
import com.intern.digitallendingsystem.model.LoanProduct;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanProductRepo extends JpaRepository<LoanProduct, Long> {

    List<LoanProduct> findAllByIsActiveTrueAndBankIdIsActiveTrue(Sort sort);
    LoanProduct findByIdAndIsActiveTrueAndBankIdIsActiveTrue(long id);
}
