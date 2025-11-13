package com.companion.notification.notifier.impl;

import com.companion.notification.models.InAppNotificationEntity;
import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.domain.Notification;
import com.companion.notification.repository.InAppNotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class InAppNotifier implements Notifier {

    private final InAppNotificationRepository inAppNotificationRepository;

    @Override
    public void sendNotification(Notification notification) {
        InAppNotificationEntity entity = InAppNotificationEntity.builder()
                .userId(notification.userId())
                .subject(notification.subject())
                .content(notification.content())
                .imageUrl("https://www.google.com/imgres?q=nice%20meme&imgurl=https%3A%2F%2Fcontent.imageresizer.com%2Fimages%2Fmemes%2Fnice-Michael-Rosen-meme-7.jpg&imgrefurl=https%3A%2F%2Fimageresizer.com%2Ffr%2Fg%25C3%25A9n%25C3%25A9rateur-de-m%25C3%25A8mes%2Fmodifier%2Fnice-michael-rosen&docid=99sJ8DQHdsG97M&tbnid=wsazOujkKvOy6M&w=440&h=435&hcb=2")
                .build();
        inAppNotificationRepository.save(entity);
        log.info("New in app notification for user {}", notification.userId());
    }
}
