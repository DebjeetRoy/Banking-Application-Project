package com.dev.java.bankingApplication.controller;

import com.dev.java.bankingApplication.dto.LoanUserDataResponse;
import com.dev.java.bankingApplication.dto.UserRequest;
import com.dev.java.bankingApplication.entity.LoanTransaction;
import com.dev.java.bankingApplication.exception.LoanNotFoundException;
import com.dev.java.bankingApplication.service.LoanTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanTransactionController {
    @Autowired
    private LoanTransactionService loanTransactionService;

    @PostMapping("/saveLoanData")
    public ResponseEntity<LoanUserDataResponse> createLoanTransaction(
            @RequestBody @Valid UserRequest userRequest) {
        try{
            LoanTransaction loanTransaction = loanTransactionService.createLoanTransaction(userRequest);
            LoanUserDataResponse response = new LoanUserDataResponse(loanTransaction, "Loan transaction created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            LoanUserDataResponse response = new LoanUserDataResponse(null, "Error creating loan transaction: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<LoanTransaction>> fetchUserData(){
        List<LoanTransaction> loanTransactions = loanTransactionService.fetchUserLoanDetails();
        return new ResponseEntity<>(loanTransactions, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<LoanUserDataResponse> fetchUserById(@Valid @PathVariable("id") final String id){
        try {
            LoanTransaction loanTransactionResponse = loanTransactionService.fetchUserLoanDetailsById(id);
            LoanUserDataResponse loanUserDataResponse = new LoanUserDataResponse(loanTransactionResponse, "Loan transaction retrieved successfully");
            return ResponseEntity.ok(loanUserDataResponse);
        } catch (LoanNotFoundException e) {
        LoanUserDataResponse response = new LoanUserDataResponse(null, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
