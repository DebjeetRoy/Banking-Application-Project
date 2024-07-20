package com.dev.java.bankingApplication.controller;

import com.dev.java.bankingApplication.dto.LoanUserDataResponse;
import com.dev.java.bankingApplication.dto.UserRequest;
import com.dev.java.bankingApplication.entity.LoanTransaction;
import com.dev.java.bankingApplication.exception.LoanNotFoundException;
import com.dev.java.bankingApplication.service.LoanTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "LoanTransactionController", description = "This is Loan Transaction Controller !!")
public class LoanTransactionController {
    @Autowired
    private LoanTransactionService loanTransactionService;

    @PostMapping("/saveLoanData")
    @Operation(summary = "Creation of Loan Transaction record and persisting in DB", description = "Saving Loan transaction record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New User with Loan Transaction Created !!"),
            @ApiResponse(responseCode = "200", description = "Success Message !!"),
            @ApiResponse(responseCode = "401", description = "Request not Authorized !!")
        }
    )
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
    @Operation(summary = "Fetching User Data", description = "Fetching Loan transaction record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Message !!"),
            @ApiResponse(responseCode = "401", description = "Request not Authorized !!")
        }
    )
    public ResponseEntity<List<LoanTransaction>> fetchUserData(){
        List<LoanTransaction> loanTransactions = loanTransactionService.fetchUserLoanDetails();
        return new ResponseEntity<>(loanTransactions, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Fetching User Data based upon User Id", description = "Fetching Loan transaction record as per user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Message !!"),
            @ApiResponse(responseCode = "401", description = "Request not Authorized !!")
        }
    )
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
