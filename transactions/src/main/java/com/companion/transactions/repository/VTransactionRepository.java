package com.companion.transactions.repository;

import com.companion.transactions.model.VTransaction;
import com.companion.transactions.model.VTransactionStatus;
import com.companion.transactions.model.VTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface VTransactionRepository extends JpaRepository<VTransaction, UUID> {

    List<VTransaction> findByBuyerId(Long buyerId);

    List<VTransaction> findBySellerId(Long sellerId);

    List<VTransaction> findAllBySellerIdAndBuyerId(Long sellerId, Long buyerId);

    Optional<VTransaction> findByMeetingIdAndSellerIdAndBuyerIdAndTypeAndStatus(Long meetingId, Long sellerId, Long buyerId, VTransactionType vTransactionType, VTransactionStatus vTransactionStatus);
}
