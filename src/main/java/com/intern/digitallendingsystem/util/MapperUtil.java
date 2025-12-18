package com.intern.digitallendingsystem.util;

import com.intern.digitallendingsystem.enums.LoanStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface MapperUtil {

    @Named("mapStatus")
    default LoanStatus mapStatus(String status) {
        if (status == null) return null;
        return LoanStatus.valueOf(status.toUpperCase().trim());
    }

    @Named("mapDisbursementChannel")
    default String mapDisbursementChannel(String disbursementChannel) {
        if (disbursementChannel == null) return null;
        return disbursementChannel.toUpperCase().trim();
    }

    @Named("mapRepaymentMethod")
    default String mapRepaymentMethod(String repaymentMethod) {
        if (repaymentMethod == null) return null;
        return repaymentMethod.toUpperCase().trim();
    }
}
