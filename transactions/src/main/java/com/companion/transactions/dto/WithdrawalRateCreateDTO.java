package com.companion.transactions.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalRateCreateDTO {

    @NotNull
    @PositiveOrZero
    private Integer minAmountV;

    @NotNull
    @PositiveOrZero
    // Divide the amount of virtual money by rate to get the amount in real currency
    private Double rate;

    @Nullable
    private String description;

    @NotNull
    private Boolean active;
}
