package com.mbb.transaction;


import com.mbb.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> getTransactions(String customerId, List<String> accountNumbers, String description, Pageable pageable) {
        return transactionRepository.search(customerId, accountNumbers, description, pageable);
    }

    @Transactional
    public Transaction updateTransaction(Long id, String description) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        transaction.setDescription(description);
        return transactionRepository.save(transaction);
    }
}
