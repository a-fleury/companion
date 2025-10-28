package com.companion.transactions.repository;

import com.companion.transactions.model.RTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface RTransactionRepository extends JpaRepository<RTransaction, UUID> {
    List<RTransaction> findAllByShopOffer_Id(UUID shopOfferId);
    List<RTransaction> findAllByWithdrawalRate_Id(UUID withdrawalRateId);
    List<RTransaction> findAllByShopOffer_ActiveTrue();
    List<RTransaction> findAllByUserId(UUID userId);
}
