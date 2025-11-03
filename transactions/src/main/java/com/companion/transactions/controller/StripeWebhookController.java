package com.companion.transactions.controller;

import com.companion.transactions.model.RTransactionStatus;
import com.companion.transactions.service.RTransactionService;
import com.companion.transactions.service.WalletService;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/stripe")
@RequiredArgsConstructor
public class StripeWebhookController {

    @Value("${stripe.webhookSecret}")
    private String webhookSecret;

    private final RTransactionService rTransactionService;
    private final WalletService walletService;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestHeader("Stripe-Signature") String sigHeader,
                                                @RequestBody String payload) {
        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        }

        switch (event.getType()) {
            case "payment_intent.succeeded" -> handleSucceeded(event);
            case "payment_intent.payment_failed" -> handleFailed(event);
            case "payment_intent.canceled" -> handleCanceled(event);
            default -> System.out.println("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok("Event processed");
    }

    private void handleSucceeded(Event event) {
        PaymentIntent pi = (PaymentIntent) event.getDataObjectDeserializer()
                .getObject().orElse(null);
        if (pi == null) return;

        UUID transactionId = UUID.fromString(pi.getMetadata().get("transactionId"));
        Long userId = Long.parseLong(pi.getMetadata().get("userId"));
        int amountV = Integer.parseInt(pi.getMetadata().get("amountV"));
        rTransactionService.updateStatus(transactionId, RTransactionStatus.SUCCESS);
        walletService.updateBalance(userId, amountV);
    }

    private void handleFailed(Event event) {
        PaymentIntent pi = (PaymentIntent) event.getDataObjectDeserializer()
                .getObject().orElse(null);
        if (pi == null) return;

        UUID transactionId = UUID.fromString(pi.getMetadata().get("transactionId"));
        rTransactionService.updateStatus(transactionId, RTransactionStatus.FAILED);
    }

    private void handleCanceled(Event event) {
        PaymentIntent pi = (PaymentIntent) event.getDataObjectDeserializer()
                .getObject().orElse(null);
        if (pi == null) return;

        UUID transactionId = UUID.fromString(pi.getMetadata().get("transactionId"));
        rTransactionService.updateStatus(transactionId, RTransactionStatus.CANCELED);
    }
}
