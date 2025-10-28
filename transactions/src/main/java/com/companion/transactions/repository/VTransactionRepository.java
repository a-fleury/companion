package com.companion.transactions.repository;

import com.companion.transactions.model.VTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unused")
public interface VTransactionRepository extends JpaRepository<VTransaction, UUID> {

    List<VTransaction> findByBuyerId(UUID buyerId);

    boolean existsByBuyerId(UUID userId);

    List<VTransaction> findBySellerId(UUID sellerId);

    boolean existsBySellerId(UUID userId);
}
