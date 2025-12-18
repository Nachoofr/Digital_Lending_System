package com.intern.digitallendingsystem.constants;

public class LoanApplicationApiEndpointConstants {
    public static final String api = "/api";
    public static final String LOAN_APPLICATIONS= api + "/loanApplications";
    public static final String LOAN_APPLICATION_ID= api + "/loanApplications/{id}";
    public static final String APPROVE = api + LOAN_APPLICATION_ID + "/approve";
    public static final String REJECT = api + LOAN_APPLICATION_ID + "/reject";

}
