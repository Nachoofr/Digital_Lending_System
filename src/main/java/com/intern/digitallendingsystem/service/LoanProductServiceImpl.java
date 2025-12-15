package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanProductDto;
import com.intern.digitallendingsystem.mapper.LoanProductMapper;
import com.intern.digitallendingsystem.repository.LoanProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanProductServiceImpl implements LoanProductService {
    LoanProductRepo loanProductRepo;
    LoanProductMapper loanProductMapper;

    // todo
    // implement proper logging using SL4J
    public ResponseEntity<LoanProductDto> createLoanProduct(LoanProductDto loanProductDto){
        var loanProduct = loanProductMapper.toEntity(loanProductDto);
        loanProduct.setActive(true);
        loanProductRepo.save(loanProduct);
        return new ResponseEntity<>(loanProductMapper.toDto(loanProduct), HttpStatus.CREATED);
    }


    public List<LoanProductDto> getAllLoanProducts(String sort){
        Sort sorting = (sort == null || sort.isBlank()) ? Sort.unsorted() : Sort.by(sort);
        return loanProductRepo.findAllByIsActiveTrueAndBankIdIsActiveTrue(sorting).stream()
                .map(loanProductMapper::toDto)
                .toList();
    }

    public ResponseEntity<LoanProductDto> getLoanProductById(long id ){
        var loanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (loanProduct == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loanProductMapper.toDto(loanProduct), HttpStatus.OK);
    }

    public ResponseEntity<LoanProductDto> updateLoanProduct(long id, LoanProductDto loanProductDto) {
        var loanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (loanProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        loanProduct.setActive(true);
        loanProductMapper.update(loanProductDto, loanProduct);
        loanProductRepo.save(loanProduct);
        return new ResponseEntity<>(loanProductMapper.toDto(loanProduct), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteLoanProduct(long id) {
        var loanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (loanProduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        loanProduct.setActive(false);
        loanProductRepo.save(loanProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
