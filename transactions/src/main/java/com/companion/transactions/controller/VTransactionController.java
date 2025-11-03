package com.companion.transactions.controller;

import com.companion.transactions.model.VTransaction;
import com.companion.transactions.service.VTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v-transactions")
@RequiredArgsConstructor
public class VTransactionController {

    private final VTransactionService vTransactionService;

    @GetMapping("/")
    public ResponseEntity<List<VTransaction>> getAll() {
        List<VTransaction> transactions = vTransactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/:id")
    public ResponseEntity<VTransaction> getById(@Param("id") UUID id) {
        VTransaction transaction = vTransactionService.getById(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/buyer/:buyerId")
    public ResponseEntity<List<VTransaction>> getAllByBuyerId(@Param("userId") Long userId) {
        List<VTransaction> transaction = vTransactionService.getAllByBuyerId(userId);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/buyer/:sellerId")
    public ResponseEntity<List<VTransaction>> getAllBySellerId(@Param("userId") Long sellerId) {
        List<VTransaction> transaction = vTransactionService.getAllBySellerId(sellerId);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/user/:userId")
    public ResponseEntity<List<VTransaction>> getAllByUserId(@Param("userId") Long userId) {
        List<VTransaction> transaction = vTransactionService.getAllByUserId(userId);
        return ResponseEntity.ok(transaction);
    }
}

