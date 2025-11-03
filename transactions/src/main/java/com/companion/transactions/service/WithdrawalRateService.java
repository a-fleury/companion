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

    public WithdrawalRate create(WithdrawalRateCreateDTO dto) {
        return withdrawalRateRepository.save(WithdrawalRate.builder()
                .minAmountV(dto.getMinAmountV())
                .rate(dto.getRate())
                .description(dto.getDescription())
                .active(dto.getActive())
                .build());
    }

    public WithdrawalRate getById(UUID id) {
        return withdrawalRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WithdrawalRate not found: " + id));
    }

    public WithdrawalRate getByAmount(int amount) {
        return findApplicableRate(amount);
    }

    public List<WithdrawalRate> getAll() {
        return withdrawalRateRepository.findAll();
    }

    public List<WithdrawalRate> getAllActive() {
        return withdrawalRateRepository.findAllByActiveTrue();
    }

    public List<WithdrawalRate> getAllInactive() {
        return withdrawalRateRepository.findAllByActiveFalse();
    }

    public void update(WithdrawalRateUpdateDTO dto) {
        UUID id = dto.getId();
        WithdrawalRate rate = withdrawalRateRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WithdrawalRate not found: " + id));
        rate.setMinAmountV(dto.getMinAmountV());
        rate.setRate(dto.getRate());
        rate.setDescription(dto.getDescription());
        rate.setActive(dto.getActive());
        withdrawalRateRepository.save(rate);
    }

    public void changeStatus(UUID id) {
        WithdrawalRate rate = withdrawalRateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("WithdrawalRate not found: " + id));
        rate.setActive(!rate.getActive());
        withdrawalRateRepository.save(rate);
    }

    public void delete(UUID id) {
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
