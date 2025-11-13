package com.companion.notification.notifier.impl;

import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.domain.Notification;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class PushNotifier implements Notifier {

    @Override
    public void sendNotification(Notification content) {
        // Logic to send push notification
        log.info("Sending fake push notification to: " + content.userId());
    }
    
}
