package com.mbb.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //Pessimistic Locking to prevent concurrent access.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Transaction> findById(Long id);

    List<Transaction> findByCustomerId(Integer customerId);

    List<Transaction> findByAccountNumberIn(List<String> accountNumbers);

    List<Transaction> findByDescriptionContaining(String description);

    @Query("SELECT t FROM Transaction t WHERE t.customerId = :customerId OR t.accountNumber IN (:accountNumbers) OR t.description LIKE %:description%")
    Page<Transaction> search(@Param("customerId") String customerId,
                             @Param("accountNumbers") List<String> accountNumbers,
                             @Param("description") String description,
                             Pageable pageable);


}

