package com.companion.notification.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.companion.notification.dto.out.NotificationMessage;
import com.companion.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;



    @KafkaListener(topics = "notification", groupId = "notification-group")
    public void sendNotification(NotificationMessage message) {
        log.info("Received Message in group notification-group: {}", message.value());
        notificationService.sendNotification(message);
    }

}
