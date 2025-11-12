package com.companion.transactions.service;

import com.companion.transactions.dto.VTransactionCreateDTO;
import com.companion.transactions.model.VTransaction;
import com.companion.transactions.model.VTransactionStatus;
import com.companion.transactions.model.VTransactionType;
import com.companion.transactions.repository.VTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VTransactionService {
    private final VTransactionRepository vTransactionRepository;
    private final WalletService walletService;

    public List<VTransaction> getAll() {
        return vTransactionRepository.findAll();
    }

    public VTransaction getById(UUID id) { return vTransactionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + id)); }

    public List<VTransaction> getAllByBuyerId(Long buyerId) {
        return vTransactionRepository.findByBuyerId(buyerId);
    }

    public List<VTransaction> getAllBySellerId(Long sellerId) {
        return vTransactionRepository.findBySellerId(sellerId);
    }

    public List<VTransaction> getAllByUserId(Long userId) {
        return vTransactionRepository.findAllBySellerIdAndBuyerId(userId, userId);
    }

    public void createVTransaction(VTransactionCreateDTO dto) {
        Long buyerId = dto.getBuyerId();
        Long sellerId = dto.getSellerId();
        VTransaction transaction = vTransactionRepository.save(VTransaction.builder()
                .buyerId(dto.getBuyerId())
                .sellerId(dto.getSellerId())
                .amount(dto.getAmount())
                .type(dto.getType())
                .status(dto.getStatus())
                .build());
    }

    public VTransaction validateTransaction(Long meetingId, Long sellerId, Long buyerId) {
        VTransaction transaction = findByMeetingIdAndSellerIdAndBuyerIdAndTypeAndStatus(meetingId, sellerId, buyerId, VTransactionType.PURCHASE, VTransactionStatus.PENDING);

        transaction.setStatus(VTransactionStatus.SUCCESS);
        return vTransactionRepository.save(transaction);
    }

    public VTransaction cancelTransaction(Long meetingId, Long sellerId, Long buyerId) {
        VTransaction transaction = findByMeetingIdAndSellerIdAndBuyerIdAndTypeAndStatus(meetingId, sellerId, buyerId, VTransactionType.PURCHASE, VTransactionStatus.PENDING);

        transaction.setStatus(VTransactionStatus.CANCELED);
        return vTransactionRepository.save(transaction);
    }

    private VTransaction findByMeetingIdAndSellerIdAndBuyerIdAndTypeAndStatus(Long meetingId, Long sellerId, Long buyerId, VTransactionType vTransactionType, VTransactionStatus vTransactionStatus) {
        return vTransactionRepository.findByMeetingIdAndSellerIdAndBuyerIdAndTypeAndStatus(meetingId, sellerId, buyerId, vTransactionType, vTransactionStatus)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

}
