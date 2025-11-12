package com.companion.notification.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import com.companion.notification.domain.NotificationType;
import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.factory.NotifierFactory;
import com.companion.notification.notifier.impl.EmailNotifier;
import com.companion.notification.notifier.impl.InAppNotifier;
import com.companion.notification.repository.InAppNotificationRepository;
import com.companion.notification.service.user.UserService;

class NotifierFactoryTest {
    
    @Mock
    private JavaMailSender mailSender;

    @Mock
    private UserService userService;

    @Mock
    private InAppNotificationRepository inAppNotifierRepository;

    @Test
    void getNotifier_withEmailType_returnsEmailNotifier() {
        Notifier notifier = new NotifierFactory(mailSender, userService, inAppNotifierRepository)
            .getNotifier(NotificationType.EMAIL);
        assertEquals(EmailNotifier.class, notifier.getClass());
    }

    @Test
    void getNotifer_withApplicationType_returnsInAppNotifier() {
        Notifier notifier = new NotifierFactory(mailSender, userService, inAppNotifierRepository)
            .getNotifier(NotificationType.APPLICATION);
        assertEquals(InAppNotifier.class, notifier.getClass());
    }
}
