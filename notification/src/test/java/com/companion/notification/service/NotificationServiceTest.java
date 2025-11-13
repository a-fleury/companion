package com.companion.notification.service;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.ConversionService;

import com.companion.notification.domain.NotificationType;
import com.companion.notification.dto.in.UserNotification;
import com.companion.notification.dto.out.MessageTarget;
import com.companion.notification.dto.out.NotificationMessage;
import com.companion.notification.events.SendNotificationEvent;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private TargetResolverService targetResolverService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void sendNotification_publishesEventForEachTarget() {
        MessageTarget target = new MessageTarget(List.of("u1", "u2"), "user");
        NotificationMessage message = new NotificationMessage("sub", "val", "APPLICATION", target);

        when(targetResolverService.resolveTargets(target)).thenReturn(List.of("u1", "u2"));
        when(conversionService.convert("APPLICATION", NotificationType.class)).thenReturn(NotificationType.APPLICATION);

        notificationService.sendNotification(message);

        verify(eventPublisher, times(2)).publishEvent(
                argThat(ev -> {
                    if (!(ev instanceof SendNotificationEvent)) return false;
                    SendNotificationEvent e = (SendNotificationEvent) ev;
                    UserNotification u = e.getMessage();
                    return (u.user_id().equals("u1") && u.subject().equals("sub") && u.content().equals("val"))
                            || (u.user_id().equals("u2") && u.subject().equals("sub") && u.content().equals("val"));
                })
        );
    }
}
