package com.companion.notification.notifier.contract;

import com.companion.notification.notifier.domain.Notification;

public interface Notifier {
    void sendNotification(Notification message);
}
