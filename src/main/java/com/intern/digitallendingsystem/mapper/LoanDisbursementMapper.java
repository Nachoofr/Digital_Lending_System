package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.LoanDisbursementDto;
import com.intern.digitallendingsystem.model.LoanDisbursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoanDisbursementMapper {

    @Mapping(source = "loanApplicationId.id", target = "loanApplicationId")
    LoanDisbursementDto toDto(LoanDisbursement loanDisbursement);

    @Mapping(target = "loanApplicationId", ignore = true)
    @Mapping(source = "disbursementChannel", target = "disbursementChannel", qualifiedByName = "mapDisbursementChannel")
    LoanDisbursement toEntity(LoanDisbursementDto loanDisbursementDto);
    // no source because we are directly assigning values ourselves in service so we ignore them here. it does not matter if we map them or not
    @Mapping(target = "loanApplicationId", ignore = true)
    @Mapping(source = "disbursementChannel", target = "disbursementChannel", qualifiedByName = "mapDisbursementChannel")
    void update(LoanDisbursementDto dto, @MappingTarget LoanDisbursement entity);

    @Named("mapDisbursementChannel")
    default String mapDisbursementChannel(String disbursementChannel) {
        if (disbursementChannel == null) return null;
        return disbursementChannel.toUpperCase().trim();
    }
}
