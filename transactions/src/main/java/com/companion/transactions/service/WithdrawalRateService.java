package com.companion.transactions.service;

import com.companion.transactions.dto.WithdrawalRateCreateDTO;
import com.companion.transactions.dto.WithdrawalRateUpdateDTO;
import com.companion.transactions.model.WithdrawalRate;
import com.companion.transactions.repository.WithdrawalRateRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WithdrawalRateService {
    private final WithdrawalRateRepository withdrawalRateRepository;

    public WithdrawalRate createWithdrawalRate(WithdrawalRateCreateDTO dto) {
        return withdrawalRateRepository.save(WithdrawalRate.builder()
                .minAmountV(dto.getMinAmountV())
                .rate(dto.getRate())
                .description(dto.getDescription())
                .bonus(dto.getBonus())
                .active(dto.isActive())
                .discount(dto.getDiscount())
                .build());
    }

    public WithdrawalRate getWithdrawalRateById(UUID id) {
        return withdrawalRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WithdrawalRate not found: " + id));
    }

    public WithdrawalRate getWithdrawalRateByAmount(int amount) {
        return findApplicableRate(amount);
    }

    public List<WithdrawalRate> getAllWithdrawalRates() {
        return withdrawalRateRepository.findAll();
    }

    public List<WithdrawalRate> getAllActiveWithdrawalRates() {
        return withdrawalRateRepository.findAllByActiveTrue();
    }

    public List<WithdrawalRate> getAllInactiveWithdrawalRates() {
        return withdrawalRateRepository.findAllByActiveFalse();
    }

    public void updateWithdrawalRate(WithdrawalRateUpdateDTO dto) {
        UUID id = dto.getId();
        WithdrawalRate rate = withdrawalRateRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WithdrawalRate not found: " + id));
        rate.setMinAmountV(dto.getMinAmountV());
        rate.setRate(dto.getRate());
        rate.setDescription(dto.getDescription());
        rate.setBonus(dto.getBonus());
        rate.setActive(dto.isActive());
        rate.setDiscount(dto.getDiscount());
        withdrawalRateRepository.save(rate);
    }

    public void changeWithdrawalRateStatus(UUID id) {
        WithdrawalRate rate = withdrawalRateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("WithdrawalRate not found: " + id));
        rate.setActive(!rate.isActive());
        withdrawalRateRepository.save(rate);
    }

    public void deleteWithdrawalRate(UUID id) {
        WithdrawalRate rate = withdrawalRateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("WithdrawalRate not found: " + id));
        rate.setActive(false);
        withdrawalRateRepository.delete(rate);
    }

    private WithdrawalRate findApplicableRate(int amount) {
        return withdrawalRateRepository.findAllByActiveTrue().stream()
                .sorted((r1, r2) -> Integer.compare(r2.getMinAmountV(), r1.getMinAmountV()))
                .filter(rate -> amount >= rate.getMinAmountV())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No active withdrawal rate found for amount: " + amount));
    }

}
