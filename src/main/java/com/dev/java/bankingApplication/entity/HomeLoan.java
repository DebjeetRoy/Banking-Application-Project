package com.dev.java.bankingApplication.entity;

import com.dev.java.bankingApplication.builder.LoanTransactionBuilder;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HomeLoan")
public class HomeLoan extends LoanTransaction{
    public HomeLoan(){

    }
    public HomeLoan(LoanTransactionBuilder builder) {
        super(builder);
    }
}
