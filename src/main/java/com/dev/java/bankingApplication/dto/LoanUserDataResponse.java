package com.dev.java.bankingApplication.dto;

import com.dev.java.bankingApplication.entity.LoanTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanUserDataResponse {
    private LoanTransaction loanTransaction;
    private String message;
}
