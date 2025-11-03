package com.companion.transactions.kafka;

import com.companion.transactions.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final WalletService walletService;

    @KafkaListener(topics = "user.created", groupId = "transaction-service-group")
    public void handleUserCreated(Long userId) {
        log.info("Received user.created event: {}", userId);
        try {
            walletService.createWallet(userId);
        } catch (Exception e) {
            log.error("Error while creating wallet for user {}", userId, e);
        }
    }

    @KafkaListener(topics = "user.deleted", groupId = "transaction-service-group")
    public void handleUserDeleted(Long userId) {
        log.info("Received user.deleted event: {}", userId);
        try {
            walletService.deleteWallet(userId);
        } catch (Exception e) {
            log.error("Error while deleting wallet for user {}", userId, e);
        }
    }
}
