package com.dev.java.bankingApplication.builder;

import com.dev.java.bankingApplication.entity.CarLoan;
import com.dev.java.bankingApplication.entity.HomeLoan;
import com.dev.java.bankingApplication.entity.LoanTransaction;
import com.dev.java.bankingApplication.entity.PersonalLoan;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanTransactionBuilder {
    public String borrowerName;
    public BigDecimal amount;
    public LocalDate loanDate;
    public BigDecimal interestRate;
    public int durationInMonths;
    public String loanType;
    public String email;
    public String mobileNo;
    public int age;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LoanTransactionBuilder setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
        return this;
    }

    public LoanTransactionBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LoanTransactionBuilder setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        return this;
    }

    public LoanTransactionBuilder setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public LoanTransactionBuilder setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
        return this;
    }

    public LoanTransactionBuilder setLoanType(String loanType) {
        this.loanType = loanType;
        return this;
    }

    public LoanTransaction build() {
        LoanTransaction loanTransaction = switch (loanType) {
            case "Home Loan" -> new HomeLoan(this);
            case "Personal Loan" -> new PersonalLoan(this);
            case "Car Loan" -> new CarLoan(this);
            default -> throw new IllegalArgumentException("Unknown loan type: " + loanType);
        };

        loanTransaction.setBorrowerName(borrowerName);
        loanTransaction.setAmount(amount);
        loanTransaction.setLoanDate(loanDate);
        loanTransaction.setInterestRate(interestRate);
        loanTransaction.setDurationInMonths(durationInMonths);
        return loanTransaction;
    }
}
