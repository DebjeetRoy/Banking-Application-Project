package com.dev.java.bankingApplication.repository;

import com.dev.java.bankingApplication.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {
}
