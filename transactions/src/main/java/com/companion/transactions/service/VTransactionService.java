package com.companion.transactions.service;

import com.companion.transactions.dto.VTransactionCreateDTO;
import com.companion.transactions.model.VTransaction;
import com.companion.transactions.repository.VTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class VTransactionService {
    private final VTransactionRepository vTransactionRepository;

    public List<VTransaction> getAll() {
        return vTransactionRepository.findAll();
    }

    public List<VTransaction> getAllByBuyerId(UUID buyerId) {
        return vTransactionRepository.findByBuyerId(buyerId);
    }

    public List<VTransaction> getAllBySellerId(UUID sellerId) {
        return vTransactionRepository.findBySellerId(sellerId);
    }

    public VTransaction createVTransaction(VTransactionCreateDTO dto) {
        VTransaction transaction = VTransaction.builder()
                .buyerId(dto.getBuyerId())
                .sellerId(dto.getSellerId())
                .amount(dto.getAmount())
                .type(dto.getType())
                .status(dto.getStatus())
                .build();
        return vTransactionRepository.save(transaction);
    }

}
