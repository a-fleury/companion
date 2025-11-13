package com.companion.notification.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.companion.notification.models.InAppNotificationEntity;
import com.companion.notification.service.InAppNotificationService;

@ExtendWith(MockitoExtension.class)
class InAppNotificationControllerTest {

    @Mock
    private InAppNotificationService inAppNotificationService;

    private InAppNotificationController controller;

    @BeforeEach
    void setUp() {
        controller = new InAppNotificationController(inAppNotificationService);
    }

    @Test
    void getAllAvailableNotificationForCurrentUser_delegatesToService() {
        List<InAppNotificationEntity> expected = List.of(
                InAppNotificationEntity.builder()
                        .userId("123")
                        .subject("subj")
                        .content("content")
                        .imageUrl("http://example.com/img.jpg")
                        .build());

        when(inAppNotificationService.getAllNotificationsForUser("123")).thenReturn(expected);

        var result = controller.getAllAvailableNotificationForCurrentUser();

        assertEquals(expected, result);
    }
}
