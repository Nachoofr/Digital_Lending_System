package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.LoanProductDto;
import com.intern.digitallendingsystem.mapper.LoanProductMapper;
import com.intern.digitallendingsystem.repository.LoanProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanProductServiceImpl implements LoanProductService {
    LoanProductRepo loanProductRepo;
    LoanProductMapper loanProductMapper;

    public LoanProductDto createLoanProduct(LoanProductDto loanProductDto){
        var loanProduct = loanProductMapper.toEntity(loanProductDto);
        loanProduct.setActive(true);
        loanProductRepo.save(loanProduct);
        return loanProductMapper.toDto(loanProduct);
    }


    public List<LoanProductDto> getAllLoanProducts(String sort){
        Sort sorting = (sort == null || sort.isBlank()) ? Sort.unsorted() : Sort.by(sort);
        return loanProductRepo.findAllByIsActiveTrueAndBankIdIsActiveTrue(sorting).stream()
                .map(loanProductMapper::toDto)
                .toList();
    }

    public LoanProductDto getLoanProductById(long id ){
        var loanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (loanProduct == null){
            return  null;
        }
        return loanProductMapper.toDto(loanProduct);
    }

    public LoanProductDto updateLoanProduct(long id, LoanProductDto loanProductDto) {
        if (!loanProductRepo.existsById(id)) {
            return null;
        }
        var loanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        loanProduct.setActive(true);
        loanProductMapper.update(loanProductDto, loanProduct);
        loanProductRepo.save(loanProduct);
        return loanProductMapper.toDto(loanProduct);
    }

    public boolean deleteLoanProduct(long id) {
        var loanProduct = loanProductRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
        if (loanProduct == null){
            return false;
        }
        loanProduct.setActive(false);
        loanProductRepo.save(loanProduct);
        return true;
    }



}
