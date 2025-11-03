package com.companion.message.service.contract;

public interface MessageService {
    void storeMessage(String message, String messageType, String senderId, String receiverId);
    void markMessageAsReadFromUser(Integer conversationId, String userId);
}
