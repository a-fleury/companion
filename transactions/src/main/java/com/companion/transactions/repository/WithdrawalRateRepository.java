package com.companion.transactions.repository;

import com.companion.transactions.model.WithdrawalRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface WithdrawalRateRepository extends JpaRepository<WithdrawalRate, UUID> {
    List<WithdrawalRate> findAllByActiveTrue();
    List<WithdrawalRate> findAllByActiveFalse();
}
