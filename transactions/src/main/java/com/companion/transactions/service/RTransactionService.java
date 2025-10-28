package com.companion.transactions.service;

import com.companion.transactions.dto.RTransactionCreateDTO;
import com.companion.transactions.model.*;
import com.companion.transactions.repository.RTransactionRepository;
import com.companion.transactions.repository.ShopOfferRepository;
import com.companion.transactions.repository.WithdrawalRateRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RTransactionService {

    private final RTransactionRepository transactionRepository;
    private final ShopOfferRepository shopOfferRepository;
    private final WithdrawalRateRepository withdrawalRateRepository;

    public List<RTransaction> getAll() {
        return transactionRepository.findAll();
    }

    public List<RTransaction> getAllTransactionsByUserId(UUID userId) {
        return transactionRepository.findAllByUserId(userId);
    }
    
    public RTransaction getRTransactionById(UUID transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + transactionId));
    }

    public void updateRTransactionStatus(UUID transactionId, RTransactionStatus status) {
        RTransaction transaction = getRTransactionById(transactionId);
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }

    public RTransaction createWithdrawal(RTransactionCreateDTO dto) {
        if (dto.getWithdrawalRateId().isEmpty()) {
            throw new IllegalArgumentException("ShopOffer cannot be null when making a DEPOSIT");
        }

        WithdrawalRate withdrawalRate = findWithdrawalRateById(dto.getWithdrawalRateId().get());

        RTransaction transaction = RTransaction.builder()
                .userId(dto.getUserId())
                .amountR(dto.getAmountR())
                .amountV(dto.getAmountV())
                .type(RTransactionType.DEPOSIT)
                .withdrawalRate(withdrawalRate)
                .status(RTransactionStatus.PENDING)
                .build();
        return transactionRepository.save(transaction);
    }

    public RTransaction createDeposit(RTransactionCreateDTO dto) {
        if (dto.getShopOfferId().isEmpty()) {
            throw new IllegalArgumentException("ShopOffer cannot be null when making a DEPOSIT");
        }

        ShopOffer shopOffer = findShopOfferById(dto.getShopOfferId().get());

        RTransaction transaction = RTransaction.builder()
                .userId(dto.getUserId())
                .amountR(dto.getAmountR())
                .amountV(dto.getAmountV())
                .type(RTransactionType.DEPOSIT)
                .shopOffer(shopOffer)
                .status(RTransactionStatus.PENDING)
                .build();
        return transactionRepository.save(transaction);
    }

    private ShopOffer findShopOfferById(UUID shopOfferId) {
        return shopOfferRepository.findById(shopOfferId)
                .orElseThrow(() -> new IllegalArgumentException("ShopOffer not found: " + shopOfferId));
    }

    private WithdrawalRate findWithdrawalRateById(UUID withdrawalRateId) {
        return withdrawalRateRepository.findById(withdrawalRateId)
                .orElseThrow(() -> new IllegalArgumentException("WithdrawalRate not found: " + withdrawalRateId));
    }


    private UUID f(UUID i) {
        if (i == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        return i;
    }
}

