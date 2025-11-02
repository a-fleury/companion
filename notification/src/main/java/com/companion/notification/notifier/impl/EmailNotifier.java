package com.companion.notification.notifier.impl;

import com.companion.notification.notifier.contract.Notifier;

public class EmailNotifier implements Notifier {

    @Override
    public void sendNotification(String content) {
        // Logic to send email notification
        System.out.println("Sending email notification with content: " + content);
    }
    
}
