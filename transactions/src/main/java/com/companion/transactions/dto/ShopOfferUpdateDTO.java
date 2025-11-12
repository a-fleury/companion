package com.companion.transactions.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ShopOfferUpdateDTO {

    private UUID id;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotNull
    @PositiveOrZero
    private Integer amountV;

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

