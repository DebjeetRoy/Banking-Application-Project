package com.dev.java.bankingApplication.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "build") // For Builder Design Pattern
@NoArgsConstructor
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "borrowerName can't be null")
    @Size(min = 3)
    private String borrowerName;

    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Email(message = "Invalid email id")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered !!")
    private String mobileNo;

    @NotNull(message = "Invalid age")
    @Min(18)
    @Max(60)
    private int age;

    @NotNull(message = "Loan date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate loanDate;

    @NotNull(message = "Interest rate is required")
    private BigDecimal interestRate;

    @NotNull(message = "Duration in months is required")
    @Min(value = 1, message = "Duration in months must be at least 1")
    private int durationInMonths;

    @NotNull(message = "Loan Type can't be null !!")
    private String loanType;
}
