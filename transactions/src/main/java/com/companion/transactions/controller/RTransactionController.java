package com.companion.transactions.controller;

import com.companion.transactions.dto.RTransactionCreateDTO;
import com.companion.transactions.model.RTransaction;
import com.companion.transactions.service.RTransactionService;
import com.companion.transactions.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/rtransactions")
@RequiredArgsConstructor
class RTransactionController {

    private final StripeService stripeService;
    private final RTransactionService rTransactionService;

    @GetMapping("/")
    public ResponseEntity<List<RTransaction>> getAllTransactions() {
        List<RTransaction> transactions = rTransactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/:id")
    public ResponseEntity<RTransaction> getTransactionById(@Param("id") UUID id) {
        RTransaction transaction = rTransactionService.getRTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/user/:userId")
    public ResponseEntity<List<RTransaction>> getAllTransactionsByUserId(@Param("userId") UUID userId) {
        List<RTransaction> transaction = rTransactionService.getAllTransactionsByUserId(userId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody RTransactionCreateDTO dto) throws Exception {
        Map<String, String> result = stripeService.createPaymentIntent(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Map<String, String>> createWithdrawal(@RequestBody RTransactionCreateDTO dto) {
        stripeService.simulateWithdrawal(dto);
        return ResponseEntity.ok(Map.of("success", "Withdrawal simulated"));
    }
}
