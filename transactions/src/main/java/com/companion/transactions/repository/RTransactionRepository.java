package com.companion.transactions.repository;

import com.companion.transactions.model.RTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface RTransactionRepository extends JpaRepository<RTransaction, UUID> {
    List<RTransaction> findAllByUserId(Long userId);
}
