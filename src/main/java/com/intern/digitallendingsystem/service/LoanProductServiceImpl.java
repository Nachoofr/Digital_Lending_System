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


}
