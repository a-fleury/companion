package com.companion.transactions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingUpdatedEvent {

    @NotNull
    Long meetingId;

    @NotNull
    Long buyerId;

    @NotNull
    Long sellerId;
}
