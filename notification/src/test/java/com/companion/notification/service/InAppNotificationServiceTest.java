package com.companion.notification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.companion.notification.models.InAppNotificationEntity;
import com.companion.notification.repository.InAppNotificationRepository;

@ExtendWith(MockitoExtension.class)
class InAppNotificationServiceTest {

    @Mock
    private InAppNotificationRepository inAppNotificationRepository;

    @InjectMocks
    private InAppNotificationService inAppNotificationService;

    @Test
    void getAllNotificationsForUser_returnsNotifications() {
        String userId = "user-1";
        InAppNotificationEntity n1 = InAppNotificationEntity.builder()
                .id(1L)
                .userId(userId)
                .subject("Subject")
                .content("Content")
                .imageUrl(null)
                .isRead(false)
                .build();

        List<InAppNotificationEntity> expected = List.of(n1);
        when(inAppNotificationRepository.findByUserId(userId)).thenReturn(expected);

        Collection<InAppNotificationEntity> actual = inAppNotificationService.getAllNotificationsForUser(userId);

        assertNotNull(actual, "Result should not be null");
        assertEquals(1, actual.size(), "Should return one notification");
        assertTrue(actual.contains(n1), "Returned collection should contain the expected notification");
        verify(inAppNotificationRepository).findByUserId(userId);
    }

    @Test
    void getAllNotificationsForUser_returnsEmptyWhenNone() {
        String userId = "unknown-user";
        when(inAppNotificationRepository.findByUserId(userId)).thenReturn(List.of());

        Collection<InAppNotificationEntity> actual = inAppNotificationService.getAllNotificationsForUser(userId);

        assertNotNull(actual, "Result should not be null even if empty");
        assertEquals(0, actual.size(), "Should return an empty collection when no notifications exist");
        verify(inAppNotificationRepository).findByUserId(userId);
    }
}
