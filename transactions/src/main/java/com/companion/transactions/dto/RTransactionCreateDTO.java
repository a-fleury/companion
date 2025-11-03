package com.companion.transactions.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class RTransactionCreateDTO {

    @NotNull
    private Long userId;

    @NotNull
    @PositiveOrZero
    private Double amountR;

    @NotNull
    @PositiveOrZero
    private int amountV;


    @Nullable
    private UUID withdrawalRateId;

    @Nullable
    private UUID shopOfferId;

    public Optional<UUID> getWithdrawalRateId() {
        return Optional.ofNullable(withdrawalRateId);
    }

    public Optional<UUID> getShopOfferId() {
        return Optional.ofNullable(shopOfferId);
    }

}
