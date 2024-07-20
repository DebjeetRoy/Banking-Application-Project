package com.dev.java.bankingApplication.entity;

import com.dev.java.bankingApplication.builder.LoanTransactionBuilder;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PersonalLoan")
public class PersonalLoan extends LoanTransaction{
    public PersonalLoan(){

    }
    public PersonalLoan(LoanTransactionBuilder builder) {
        super(builder);
    }
}
