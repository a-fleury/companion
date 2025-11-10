package com.companion.notification.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companion.notification.models.InAppNotificationEntity;
import com.companion.notification.service.InAppNotificationService;
import com.companion.notification.service.user.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/in-app-notifications")
@RequiredArgsConstructor
public class InAppNotificationController {

    private final InAppNotificationService inAppNotificationService;

    @GetMapping()
    public Collection<InAppNotificationEntity> getAllAvailableNotificationForCurrentUser() {
        return inAppNotificationService.getAllNotificationsForUser("123");
    }
    
    
}
