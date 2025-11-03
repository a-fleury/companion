package com.companion.transactions.service;

import com.companion.transactions.model.Wallet;
import com.companion.transactions.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for user " + userId));
    }

    public void updateBalance(Long userId, int delta) {
        Wallet wallet = getWalletByUserId(userId);
        wallet.setBalance(wallet.getBalance() + delta);
        walletRepository.save(wallet);
    }

    public Wallet createWallet(Long userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        return walletRepository.save(wallet);
    }

    public void deleteWallet(Long userId) {
        Wallet wallet = getWalletByUserId(userId);
        walletRepository.delete(wallet);
    }

    public List<Wallet> getAll() {
        return walletRepository.findAll();
    }
}

