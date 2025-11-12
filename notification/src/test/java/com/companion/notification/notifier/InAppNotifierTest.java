package com.companion.notification.notifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.companion.notification.models.InAppNotificationEntity;
import com.companion.notification.notifier.domain.Notification;
import com.companion.notification.notifier.impl.InAppNotifier;
import com.companion.notification.repository.InAppNotificationRepository;

@ExtendWith(MockitoExtension.class)
class InAppNotifierTest {

    @Mock
    private InAppNotificationRepository repo;

    @Mock
    private Notification notification;

    @Captor
    private ArgumentCaptor<InAppNotificationEntity> entityCaptor;

    private InAppNotifier notifier;

    @BeforeEach
    void setUp() {
        notifier = new InAppNotifier(repo);
    }

    @Test
    void sendNotification_savesEntityWithExpectedFields() {
        when(notification.userId()).thenReturn("user-1");
        when(notification.subject()).thenReturn("Test Subject");
        when(notification.content()).thenReturn("Test content body");

        notifier.sendNotification(notification);

        verify(repo).save(entityCaptor.capture());
        InAppNotificationEntity saved = entityCaptor.getValue();

        assertNotNull(saved, "Expected an entity to be saved");

        assertEquals("user-1", saved.getUserId());
        assertEquals("Test Subject", saved.getSubject());
        assertEquals("Test content body", saved.getContent());
        assertNotNull(saved.getImageUrl());
        assertTrue(saved.getImageUrl().contains("nice-Michael-Rosen-meme"),
                "Expected imageUrl to contain meme identifier");
    }

    @Test
    void sendNotification_nullNotification_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notifier.sendNotification(null));
    }
}
