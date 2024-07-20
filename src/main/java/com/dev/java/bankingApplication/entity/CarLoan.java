package com.dev.java.bankingApplication.entity;

import com.dev.java.bankingApplication.builder.LoanTransactionBuilder;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CarLoan")
public class CarLoan extends LoanTransaction{
    public CarLoan(){

    }
    public CarLoan(LoanTransactionBuilder builder) {
        super(builder);
    }
}
