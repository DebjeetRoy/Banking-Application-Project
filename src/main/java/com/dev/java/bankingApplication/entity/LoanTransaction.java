package com.dev.java.bankingApplication.entity;

import com.dev.java.bankingApplication.builder.LoanTransactionBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "LOAN_TRANSACTION"
, uniqueConstraints = {
@UniqueConstraint(columnNames = {"borrower_name", "email", "loan_type", "loan_date"})})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="borrower_name")
    private String borrowerName;

    private BigDecimal amount;

    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    private Integer age;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name="duration_in_months")
    private int durationInMonths;

    @Column(name = "loan_type")
    private String loanType;

    public LoanTransaction(LoanTransactionBuilder builder) {
        this.borrowerName = builder.borrowerName;
        this.amount = builder.amount;
        this.loanDate = builder.loanDate;
        this.interestRate = builder.interestRate;
        this.durationInMonths = builder.durationInMonths;
        this.loanType = builder.loanType;
        this.age=builder.age;
        this.mobileNo=builder.mobileNo;
        this.email=builder.email;
    }
}
