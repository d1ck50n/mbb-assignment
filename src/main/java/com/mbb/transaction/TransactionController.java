package com.mbb.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Page<Transaction>> getTransactions(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) List<String> accountNumbers,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal Object principal // Assuming you have an authentication mechanism in place
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Transaction> transactions = transactionService.getTransactions(customerId, accountNumbers, description, pageable);
        System.out.println("Account ID: " + accountNumbers);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody String description) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, description);
        return ResponseEntity.ok(updatedTransaction);
    }
}

