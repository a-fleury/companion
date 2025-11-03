package com.companion.transactions.controller;

import com.companion.transactions.model.Wallet;
import com.companion.transactions.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/logged-user")
    public ResponseEntity<Wallet> getMyWallet(@RequestHeader("X-User-Id") Long userId) {
        Wallet wallet = walletService.getWalletByUserId(userId);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Wallet>> getAll() {
        List<Wallet> wallets = walletService.getAll();
        return ResponseEntity.ok(wallets);
    }
}
