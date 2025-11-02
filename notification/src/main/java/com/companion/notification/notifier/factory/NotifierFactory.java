package com.companion.notification.notifier.factory;

import org.springframework.stereotype.Component;

import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.impl.EmailNotifier;
import com.companion.notification.notifier.impl.PushNotifier;

@Component
public class NotifierFactory {
    

    public Notifier getNotifier(String type) {
        if (type.equals("email")) {
            return new EmailNotifier();
        } else if (type.equals("push")) {
            return new PushNotifier();
        }
        throw new UnsupportedOperationException("Notifier type not supported: " + type);
    }
}
