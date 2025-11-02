package com.companion.notification.notifier.impl;

import com.companion.notification.notifier.contract.Notifier;

public class PushNotifier implements Notifier {

    @Override
    public void sendNotification(String content) {
        // Logic to send push notification
        System.out.println("Sending push notification with content: " + content);
    }
    
}
