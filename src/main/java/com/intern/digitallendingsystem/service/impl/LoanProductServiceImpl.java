package com.intern.digitallendingsystem.service.impl;

import com.intern.digitallendingsystem.dto.LoanProductDto;
import com.intern.digitallendingsystem.mapper.LoanProductMapper;
import com.intern.digitallendingsystem.model.LoanProduct;
import com.intern.digitallendingsystem.repository.LoanProductRepo;
import com.intern.digitallendingsystem.service.LoanProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<LoanProduct> optionalLoanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (optionalLoanProduct.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanProduct loanProduct = optionalLoanProduct.get();
        return new ResponseEntity<>(loanProductMapper.toDto(loanProduct), HttpStatus.OK);
    }

    public ResponseEntity<LoanProductDto> updateLoanProduct(long id, LoanProductDto loanProductDto) {
        Optional<LoanProduct> optionalLoanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (optionalLoanProduct.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanProduct loanProduct = optionalLoanProduct.get();
        loanProduct.setActive(true);
        loanProductMapper.update(loanProductDto, loanProduct);
        loanProductRepo.save(loanProduct);
        return new ResponseEntity<>(loanProductMapper.toDto(loanProduct), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteLoanProduct(long id) {
        Optional<LoanProduct> optionalLoanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (optionalLoanProduct.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoanProduct loanProduct = optionalLoanProduct.get();
        loanProduct.setActive(false);
        loanProductRepo.save(loanProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
