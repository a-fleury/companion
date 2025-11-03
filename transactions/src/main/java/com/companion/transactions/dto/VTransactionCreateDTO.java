package com.companion.transactions.dto;

import com.companion.transactions.model.VTransactionStatus;
import com.companion.transactions.model.VTransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class VTransactionCreateDTO {

    @NotNull
    private Long buyerId;

    @NotNull
    private Long sellerId;

    @NotNull Long meetingId;

    @PositiveOrZero
    private int amount;

    @NotNull
    private VTransactionType type;

    @NotNull
    private VTransactionStatus status;
}
