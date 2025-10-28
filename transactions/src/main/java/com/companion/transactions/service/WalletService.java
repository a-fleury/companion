package com.companion.transactions.service;

import com.companion.transactions.model.Wallet;
import com.companion.transactions.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet getWalletByUserId(UUID userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for user " + userId));
    }

    public void updateBalance(UUID userId, int delta) {
        Wallet wallet = getWalletByUserId(userId);
        wallet.setBalance(wallet.getBalance() + delta);
        walletRepository.save(wallet);
    }

    public Wallet createWallet(UUID userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        return walletRepository.save(wallet);
    }

    public void deleteWallet(UUID userId) {
        Wallet wallet = getWalletByUserId(userId);
        walletRepository.delete(wallet);
    }
}

