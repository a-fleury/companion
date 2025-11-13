package com.companion.notification.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.companion.notification.dto.out.NotificationMessage;
import com.companion.notification.service.NotificationService;

@ExtendWith(MockitoExtension.class)
class NotificationListenerTest {

    @Mock
    private NotificationService notificationService;

    @Mock
    private NotificationMessage message;

    private NotificationListener listener;

    @BeforeEach
    void setUp() {
        listener = new NotificationListener(notificationService);
    }

    @Test
    void sendNotification_delegatesToService() {
        when(message.value()).thenReturn("test-payload");
        listener.sendNotification(message);
        verify(notificationService).sendNotification(message);
    }
}
