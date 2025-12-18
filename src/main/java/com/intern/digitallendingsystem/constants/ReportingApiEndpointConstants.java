package com.intern.digitallendingsystem.constants;

public class ReportingApiEndpointConstants {
    public static final String REPORTS = "/api/reports";
    public static final String LOAN_SUMMARY = REPORTS + BankApiEndpointConstants.BANK_ID + "/loan-summary";
    public static final  String CUSTOMER_LOAN_SUMMARY = REPORTS + BankApiEndpointConstants.BANK_ID + "/loans";
}
