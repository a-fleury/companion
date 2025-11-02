package com.companion.notification.service;

import java.util.Collection;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.companion.notification.dto.in.UserMessage;
import com.companion.notification.dto.out.NotificationMessage;
import com.companion.notification.events.SendNotificationEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ApplicationEventPublisher eventPublisher;
    private final TargetResolverService targetResolverService;

    public void sendNotification(NotificationMessage message) {
        Collection<String> targets = targetResolverService.resolveTargets(message.messageTarget());
        for (String id : targets) {
            eventPublisher.publishEvent(new SendNotificationEvent(this, new UserMessage(id, message.value(), message.notificationType())));
        }
    }
    
}
