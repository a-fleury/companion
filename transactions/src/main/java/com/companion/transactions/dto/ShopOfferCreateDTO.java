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

    @NotNull
    @PositiveOrZero
    private Integer discount;

    @NotNull
    @PositiveOrZero
    private Integer bonus;

    @NotNull
    private Boolean active;
}
