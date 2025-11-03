package com.companion.transactions.kafka;

import com.companion.transactions.dto.BookingCreatedEvent;
import com.companion.transactions.dto.BookingUpdatedEvent;
import com.companion.transactions.dto.VTransactionCreateDTO;
import com.companion.transactions.model.VTransactionStatus;
import com.companion.transactions.model.VTransactionType;
import com.companion.transactions.service.VTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingEventConsumer {

    private final VTransactionService vTransactionService;

    @KafkaListener(topics = "booking.created", groupId = "transaction-service-group")
    public void handleBookingCreated(BookingCreatedEvent event) {
        log.info("Received booking.created event: {}", event);
        try {
            VTransactionCreateDTO dto = VTransactionCreateDTO.builder()
                    .meetingId(event.getMeetingId())
                    .amount(event.getAmount())
                    .buyerId(event.getBuyerId())
                    .sellerId(event.getSellerId())
                    .type(VTransactionType.PURCHASE)
                    .status(VTransactionStatus.PENDING)
                    .build();
            vTransactionService.createVTransaction(dto);
        } catch (Exception e) {
            log.error("Error while processing transaction for encounter {}", event.getMeetingId(), e);
        }
    }

    @KafkaListener(topics = "booking.success", groupId = "transaction-service-group")
    public void handleBookingSuccess(BookingUpdatedEvent event) {
        log.info("Received booking.success event: {}", event);
        try {
            vTransactionService.validateTransaction(event.getMeetingId(), event.getSellerId(), event.getBuyerId());
        } catch (Exception e) {
            log.error("Error while processing transaction for encounter {}", event.getMeetingId(), e);
        }
    }

    @KafkaListener(topics = "booking.canceled", groupId = "transaction-service-group")
    public void handleBookingCanceled(BookingUpdatedEvent event) {
        log.info("Received meeting.canceled event: {}", event);
        try {
            vTransactionService.cancelTransaction(event.getMeetingId(), event.getSellerId(), event.getBuyerId());
        } catch (Exception e) {
            log.error("Error while processing transaction for encounter {}", event.getMeetingId(), e);
        }
    }
}
