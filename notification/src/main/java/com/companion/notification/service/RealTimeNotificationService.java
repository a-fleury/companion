package com.companion.notification.service;

import java.util.Set;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.companion.notification.config.websocket.WebsocketSessionRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class RealTimeNotificationService {
    
    private final SimpMessagingTemplate messagingTemplate;
    private final WebsocketSessionRegistry sessionRegistry;

    public void sendToUser(String userId, Object payload) {
    log.info("Tentative d'envoi de notification à l'utilisateur: {}", userId);
    log.info("Utilisateurs connectés: {}", sessionRegistry.getAllUsers());
    messagingTemplate.convertAndSendToUser("1", "/queue/notifications", payload);
    }
}
