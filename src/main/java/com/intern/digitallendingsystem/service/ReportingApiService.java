package com.intern.digitallendingsystem.service;

import com.intern.digitallendingsystem.dto.ReportingBankApiDto;
import com.intern.digitallendingsystem.dto.ReportingCustomerApiDto;


public interface ReportingApiService {

    ReportingBankApiDto bankLoanSummary(long id);
    ReportingCustomerApiDto customerLoanSummary(long id);
}
