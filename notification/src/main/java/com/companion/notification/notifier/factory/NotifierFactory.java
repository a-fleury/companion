package com.companion.notification.notifier.factory;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.companion.notification.domain.NotificationType;
import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.impl.EmailNotifier;
import com.companion.notification.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotifierFactory {

    private final JavaMailSender mailSender;
    private final UserService userService;

    public Notifier getNotifier(NotificationType type) {
        if (type.equals(NotificationType.EMAIL)) {
            return new EmailNotifier(mailSender, userService);
        }
        throw new UnsupportedOperationException("Notifier type not supported: " + type);
    }
}
