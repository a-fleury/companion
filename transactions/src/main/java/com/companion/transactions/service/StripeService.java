package com.companion.transactions.service;

import com.companion.transactions.dto.RTransactionCreateDTO;
import com.companion.transactions.model.RTransaction;
import com.companion.transactions.model.RTransactionStatus;
import com.companion.transactions.model.WithdrawalRate;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StripeService {
    private final RTransactionService rTransactionService;
    private final WalletService walletService;
    private final WithdrawalRateService withdrawalRateService;

    public Map<String, String> createPaymentIntent(RTransactionCreateDTO dto) throws Exception {
        // Create a DB record of transaction
        RTransaction transaction = rTransactionService.createDeposit(dto);

        // Create payment intent on Stripe
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (dto.getAmountR() * 100)) // en centimes
                .setCurrency("eur")
                .addPaymentMethodType("card")
                .putMetadata("transactionId", transaction.getId().toString())
                .putMetadata("userId", dto.getUserId().toString())
                .putMetadata("amountV", String.valueOf(dto.getAmountV()))
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Return client secret for frontend
        return Map.of("clientSecret", paymentIntent.getClientSecret());
    }

    public void simulateWithdrawal(RTransactionCreateDTO dto) {
        // Create a DB record of transaction
        WithdrawalRate correctRate = withdrawalRateService.getWithdrawalRateByAmount(dto.getAmountV());
        UUID providedRateId = dto.getWithdrawalRateId()
                .orElseThrow(() -> new IllegalArgumentException("Withdrawal rate ID is required"));

        if (!correctRate.getId().equals(providedRateId)) {
            throw new IllegalArgumentException("Incorrect withdrawal rate id provided");
        }

        RTransaction transaction = rTransactionService.createWithdrawal(dto);

        rTransactionService.updateRTransactionStatus(transaction.getId(), RTransactionStatus.SUCCESS);
        walletService.updateBalance(transaction.getUserId(), -transaction.getAmountV());

    }
}
