package com.companion.transactions.controller;

import com.companion.transactions.dto.WithdrawalRateCreateDTO;
import com.companion.transactions.dto.WithdrawalRateUpdateDTO;
import com.companion.transactions.model.WithdrawalRate;
import com.companion.transactions.service.WithdrawalRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/withdrawal-rates")
@RequiredArgsConstructor
public class WithdrawalRateController {

    private final WithdrawalRateService withdrawalRateService;

    @GetMapping("/")
    public ResponseEntity<List<WithdrawalRate>> getAllWithdrawalRates() {
        List<WithdrawalRate> rates = withdrawalRateService.getAllWithdrawalRates();
        return ResponseEntity.ok(rates);
    }

    @GetMapping("/active")
    public ResponseEntity<List<WithdrawalRate>> getAllActiveWithdrawalRates() {
        List<WithdrawalRate> rates = withdrawalRateService.getAllActiveWithdrawalRates();
        return ResponseEntity.ok(rates);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<WithdrawalRate>> getAllInactiveWithdrawalRates() {
        List<WithdrawalRate> rates = withdrawalRateService.getAllInactiveWithdrawalRates();
        return ResponseEntity.ok(rates);
    }

    @GetMapping("/:id")
    public ResponseEntity<WithdrawalRate> getWithdrawalRateById(@Param("id") UUID id) {
        WithdrawalRate rate = withdrawalRateService.getWithdrawalRateById(id);
        return ResponseEntity.ok(rate);
    }

    @PostMapping("/")
    public ResponseEntity<WithdrawalRate> createWithdrawalRate(@RequestBody WithdrawalRateCreateDTO dto) {
        WithdrawalRate rate = withdrawalRateService.createWithdrawalRate(dto);
        return ResponseEntity.ok(rate);
    }

    @PatchMapping("/")
    public ResponseEntity<String> updateWithdrawalRate(@RequestBody WithdrawalRateUpdateDTO dto) {
        withdrawalRateService.updateWithdrawalRate(dto);
        return ResponseEntity.ok("Withdrawal rate updated");
    }

    @DeleteMapping("/:id")
    public ResponseEntity<String> deleteWithdrawalRate(@Param("id") UUID id) {
        withdrawalRateService.deleteWithdrawalRate(id);
        return ResponseEntity.ok("Withdrawal rate deleted");
    }

}
