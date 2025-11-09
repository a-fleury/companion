package com.companion.notification.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.companion.notification.events.SendNotificationEvent;
import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.domain.Notification;
import com.companion.notification.notifier.factory.NotifierFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotifierFactory notifierFactory;

    @EventListener
    public void handleSendNotificationEvent(SendNotificationEvent event) {
        log.info("Received notification: {}", event.getMessage());
        Notifier notifier = notifierFactory.getNotifier(event.getMessage().type());
        Notification notification = new Notification(event.getMessage().user_id(), event.getMessage().content(), event.getMessage().content());
        notifier.sendNotification(notification);
    }
}
