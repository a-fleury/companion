package com.companion.transactions.repository;

import com.companion.transactions.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unused")
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByUserId(Long userId);
    Optional<Wallet> findByUserIdAndId(Long userId, UUID id);
    boolean existsByUserId(Long userId);
    boolean existsByUserIdAndId(Long userId, UUID id);
}
