package com.companion.transactions.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WithdrawalRateUpdateDTO {
    @NotNull
    private UUID id;

    @PositiveOrZero
    private int minAmountV;

    @NotNull
    @PositiveOrZero
    // Divide the amount of virtual money by rate to get the amount in real currency
    private Double rate;

    @Nullable
    private String description;

    @PositiveOrZero
    private int discount;

    @PositiveOrZero
    private int bonus;

    private boolean active;
}

