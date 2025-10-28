package com.companion.transactions.dto;

import com.companion.transactions.model.VTransactionStatus;
import com.companion.transactions.model.VTransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VTransactionCreateDTO {

    @NotNull
    private UUID buyerId;

    @NotNull
    private UUID sellerId;

    @PositiveOrZero
    private int amount;

    @NotNull
    private VTransactionType type;

    @NotNull
    private VTransactionStatus status;
}
