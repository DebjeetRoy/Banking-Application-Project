package com.dev.java.bankingApplication.service;

import com.dev.java.bankingApplication.dto.UserRequest;
import com.dev.java.bankingApplication.entity.LoanTransaction;
import com.dev.java.bankingApplication.exception.LoanNotFoundException;
import com.dev.java.bankingApplication.repository.LoanTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanTransactionService {
    @Autowired
    private LoanTransactionRepository loanTransactionRepository;

    public LoanTransaction createLoanTransaction(UserRequest userRequest) throws IllegalArgumentException{
        LoanTransaction loanTransaction = LoanTransaction.build(userRequest.getId(), userRequest.getBorrowerName()
                ,userRequest.getAmount(),userRequest.getEmail(), userRequest.getMobileNo(), userRequest.getAge(),
                userRequest.getLoanDate(), userRequest.getInterestRate(), userRequest.getDurationInMonths(), userRequest.getLoanType());

        return loanTransactionRepository.save(loanTransaction);
    }

    public List<LoanTransaction> fetchUserLoanDetails(){
        List<LoanTransaction> loanTransactionList = loanTransactionRepository.findAll();
        if(loanTransactionList.isEmpty()){
            throw new LoanNotFoundException("Loan Transactions list is empty !!");
        }else {
            return loanTransactionList;
        }
    }

    public LoanTransaction fetchUserLoanDetailsById(String id) {
        Optional<LoanTransaction> optionalLoanTransaction = loanTransactionRepository.findById(Long.valueOf(id));
        if(optionalLoanTransaction.isPresent()){
            return optionalLoanTransaction.get();
        }else {
            throw new LoanNotFoundException("Loan details for User "+id+" is not present !!");
        }
    }
}
