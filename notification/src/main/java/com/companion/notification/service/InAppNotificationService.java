package com.companion.notification.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.companion.notification.models.InAppNotificationEntity;
import com.companion.notification.repository.InAppNotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InAppNotificationService {

    private final InAppNotificationRepository inAppNotificationRepository;

    public Collection<InAppNotificationEntity> getAllNotificationsForUser(String userId) {
        log.info("Retrieving all notifications for user: {}", userId);
        return inAppNotificationRepository.findByUserId(userId);
    }
}
