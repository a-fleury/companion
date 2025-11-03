package com.companion.transactions.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingCreatedEvent {

    @NotNull
    Long meetingId;

    @NotNull
    Long buyerId;

    @NotNull
    Long sellerId;

    @PositiveOrZero
    int amount;
}
