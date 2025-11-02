package com.companion.notification.service.websocket;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class WebSocketSessionService {
    private final Map<String, Set<String>> userSessions = new ConcurrentHashMap<>();

    public void addSession(String userId, String sessionId) {
        userSessions.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(sessionId);
    }

    public void removeSession(String userId, String sessionId) {
        if (userSessions.containsKey(userId)) {
            userSessions.get(userId).remove(sessionId);
        }
    }

    public Set<String> getSessions(String userId) {
        return userSessions.getOrDefault(userId, Collections.emptySet());
    }

    public Map<String, Set<String>> getAllSessions() {
        return userSessions;
    }
}
