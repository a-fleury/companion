package com.companion.transactions.repository;

import com.companion.transactions.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unused")
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByUserId(UUID userId);
    Optional<Wallet> findByUserIdAndId(UUID userId, UUID id);
    boolean existsByUserId(UUID userId);
    boolean existsByUserIdAndId(UUID userId, UUID id);
}
