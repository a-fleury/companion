package com.companion.notification.notifier.factory;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.companion.notification.domain.NotificationType;
import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.impl.EmailNotifier;
import com.companion.notification.notifier.impl.InAppNotifier;
import com.companion.notification.repository.InAppNotificationRepository;
import com.companion.notification.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotifierFactory {

    private final JavaMailSender mailSender;
    private final UserService userService;
    private final InAppNotificationRepository inAppNotifierRepository;

    public Notifier getNotifier(NotificationType type) {
        if (type.equals(NotificationType.EMAIL)) {
            return new EmailNotifier(mailSender, userService);
        }
        if (type.equals(NotificationType.APPLICATION)) {
            return new InAppNotifier(inAppNotifierRepository);
        }
        throw new UnsupportedOperationException("Notifier type not supported: " + type);
    }
}
