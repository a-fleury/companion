package com.companion.notification.notifier;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.companion.notification.notifier.domain.Notification;
import com.companion.notification.notifier.impl.EmailNotifier;
import com.companion.notification.service.user.UserService;

@ExtendWith(MockitoExtension.class)
class EmailNotifierTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private UserService userService;

    @Mock
    private Notification notification;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> messageCaptor;

    private EmailNotifier notifier;

    @BeforeEach
    void setUp() {
        notifier = new EmailNotifier(mailSender, userService);
    }

    @Test
    void sendNotification_sendsEmailWithExpectedFields() {
        when(notification.userId()).thenReturn("1");
        when(notification.content()).thenReturn("Hello body");
        when(notification.subject()).thenReturn("Greetings");
        when(userService.getUserEmailById("1")).thenReturn("user@example.com");

        notifier.sendNotification(notification);

        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage sent = messageCaptor.getValue();

        assertEquals("app.companion.mail@gmail.com", sent.getFrom());
        assertArrayEquals(new String[] { "user@example.com" }, sent.getTo());
        assertEquals("Hello body", sent.getText());
        assertEquals("Greetings", sent.getSubject());
    }

    @Test
    void sendNotification_nullNotification_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notifier.sendNotification(null));
    }
}
