package com.companion.transactions.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShopOfferCreateDTO {
    @NotNull
    @PositiveOrZero
    private Double price;

    @NotNull
    @PositiveOrZero
    private int amountV;

    @NotNull
    private String description;

    @PositiveOrZero
    private int discount;

    @PositiveOrZero
    private int bonus;

    private boolean active;
}
