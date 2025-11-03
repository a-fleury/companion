package com.companion.message.service.impl.concrete;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.companion.message.entity.ConversationEntity;
import com.companion.message.entity.MessageEntity;
import com.companion.message.repository.MessageRepository;
import com.companion.message.service.contract.ConversationService;
import com.companion.message.service.contract.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConcreteMessageService implements MessageService{

    private final ConversationService conversationService;
    private final MessageRepository messageRepository;

    @Override
    public void storeMessage(String message,String messageType, String senderId, String receiverId) {
        ConversationEntity conversation = conversationService.getOrStartConversation(senderId, receiverId);
        MessageEntity msg = new MessageEntity();
        msg.setSenderId(senderId);
        msg.setContent(message);
        msg.setRead(false);
        msg.setConversation(conversation);
        msg.setSentAt(LocalDateTime.now());
        msg.setMessageType(messageType);
        messageRepository.save(msg);
        log.info("Storing message: {} for receiver ID: {}", message, receiverId);
    }

    @Override
    public void markMessageAsReadFromUser(Integer conversationId, String userId) {
        log.info("Marking messages as read in conversation ID: {} for user ID: {}", conversationId, userId);
        Collection<MessageEntity> messages = messageRepository.findByConversationIdAndIsReadFalse(conversationId);
        messages.forEach(msg -> {
            msg.setRead(true);
            messageRepository.save(msg);
        });
        log.info("Marked {} messages as read", messages.size());
    }
}
