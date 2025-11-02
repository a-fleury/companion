package com.companion.notification.listener.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import com.companion.notification.service.websocket.WebSocketSessionService;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class WebSocketConnectListener implements ApplicationListener<SessionConnectedEvent> {

    private final WebSocketSessionService sessionService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public WebSocketConnectListener(WebSocketSessionService sessionService, SimpMessagingTemplate messagingTemplate) {
        this.sessionService = sessionService;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        log.info("WebSocket connection established: {}, sessionId={}", event.getMessage(), "1");
        sessionService.addSession("1", "1");

        Runnable sendTask = () -> {
            String payload = "heartbeat for session " + "1" + " at " + Instant.now().toString();
            messagingTemplate.convertAndSend("/topic/notifications", payload);
            log.debug("Sent scheduled message for session {} : {}", "1", payload);
        };

        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(sendTask, 0, 5, TimeUnit.SECONDS);
        scheduledTasks.put("1", future);
    }

}
