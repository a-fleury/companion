package com.companion.notification.service;

import java.util.Collection;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.companion.notification.domain.NotificationType;
import com.companion.notification.dto.in.UserNotification;
import com.companion.notification.dto.out.NotificationMessage;
import com.companion.notification.events.SendNotificationEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ApplicationEventPublisher eventPublisher;
    private final TargetResolverService targetResolverService;
    private final ConversionService conversionService;

    public void sendNotification(NotificationMessage message) {
        NotificationType notificationType = conversionService.convert(message.notificationType(),  NotificationType.class);
        Collection<String> targets = targetResolverService.resolveTargets(message.messageTarget());
        for (String id : targets) {
            eventPublisher.publishEvent(
                new SendNotificationEvent(
                    this, 
                    new UserNotification(
                        id,
                        message.subject(),
                        message.value(), 
                        notificationType
                        )
                    )
                );
        }
    }
}
