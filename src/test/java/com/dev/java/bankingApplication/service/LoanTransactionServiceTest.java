package com.dev.java.bankingApplication.service;

import com.dev.java.bankingApplication.dto.UserRequest;
import com.dev.java.bankingApplication.entity.LoanTransaction;
import com.dev.java.bankingApplication.exception.LoanNotFoundException;
import com.dev.java.bankingApplication.repository.LoanTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanTransactionServiceTest {

    @Mock
    private LoanTransactionRepository loanTransactionRepository;

    @InjectMocks
    private LoanTransactionService loanTransactionService;

    private UserRequest userRequest;
    private LoanTransaction loanTransaction;

    @BeforeEach
    public void setUp() {
        userRequest = new UserRequest();
        userRequest.setId(1L);
        userRequest.setBorrowerName("John Doe");
        userRequest.setAmount(BigDecimal.valueOf(1000.0));
        userRequest.setEmail("johndoe@example.com");
        userRequest.setMobileNo("1234567890");
        userRequest.setAge(30);
        userRequest.setLoanDate(LocalDate.of(2024, 7, 1));
        userRequest.setInterestRate(BigDecimal.valueOf(5.0));
        userRequest.setDurationInMonths(12);
        userRequest.setLoanType("Personal");

        loanTransaction = LoanTransaction.build(
                userRequest.getId(),
                userRequest.getBorrowerName(),
                userRequest.getAmount(),
                userRequest.getEmail(),
                userRequest.getMobileNo(),
                userRequest.getAge(),
                userRequest.getLoanDate(),
                userRequest.getInterestRate(),
                userRequest.getDurationInMonths(),
                userRequest.getLoanType()
        );
    }

    @Test
    public void testCreateLoanTransaction() {
        when(loanTransactionRepository.save(any(LoanTransaction.class))).thenReturn(loanTransaction);

        LoanTransaction result = loanTransactionService.createLoanTransaction(userRequest);

        assertNotNull(result);
        assertEquals(loanTransaction, result);
        verify(loanTransactionRepository, times(1)).save(any(LoanTransaction.class));
    }

    @Test
    public void testFetchUserLoanDetails() {
        List<LoanTransaction> loanTransactionList = new ArrayList<>();
        loanTransactionList.add(loanTransaction);

        when(loanTransactionRepository.findAll()).thenReturn(loanTransactionList);

        List<LoanTransaction> result = loanTransactionService.fetchUserLoanDetails();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(loanTransactionList.size(), result.size());
        verify(loanTransactionRepository, times(1)).findAll();
    }

    @Test
    public void testFetchUserLoanDetailsWhenEmpty() {
        when(loanTransactionRepository.findAll()).thenReturn(new ArrayList<>());

        LoanNotFoundException exception = assertThrows(LoanNotFoundException.class, () -> {
            loanTransactionService.fetchUserLoanDetails();
        });

        assertEquals("Loan Transactions list is empty !!", exception.getMessage());
        verify(loanTransactionRepository, times(1)).findAll();
    }

    @Test
    public void testFetchUserLoanDetailsById() {
        when(loanTransactionRepository.findById(1L)).thenReturn(Optional.of(loanTransaction));

        LoanTransaction result = loanTransactionService.fetchUserLoanDetailsById("1");

        assertNotNull(result);
        assertEquals(loanTransaction, result);
        verify(loanTransactionRepository, times(1)).findById(1L);
    }

    @Test
    public void testFetchUserLoanDetailsByIdWhenNotFound() {
        when(loanTransactionRepository.findById(1L)).thenReturn(Optional.empty());

        LoanNotFoundException exception = assertThrows(LoanNotFoundException.class, () -> {
            loanTransactionService.fetchUserLoanDetailsById("1");
        });

        assertEquals("Loan details for User 1 is not present !!", exception.getMessage());
        verify(loanTransactionRepository, times(1)).findById(1L);
    }
}
