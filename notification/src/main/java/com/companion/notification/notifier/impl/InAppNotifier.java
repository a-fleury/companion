package com.companion.notification.notifier.impl;

import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.domain.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InAppNotifier implements Notifier {
    
    @Override
    public void sendNotification(Notification notification) {
        log.info("Sending in-app fake notification to: " + notification.userId());
    }
}
