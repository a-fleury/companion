package com.companion.message.service.impl.concrete;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.companion.message.entity.ConversationEntity;
import com.companion.message.exception.ResourceNotFoundException;
import com.companion.message.repository.ConversationRepository;
import com.companion.message.service.contract.ConversationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcreteConversationService implements ConversationService {
    
    private final ConversationRepository conversationRepository;

    @Override
    public ConversationEntity getOrStartConversation(String userId1, String userId2) {
        return conversationRepository.findConversationBetweenUsers(userId1, userId2)
            .orElseGet(() -> {
                ConversationEntity newConversation = new ConversationEntity();
                newConversation.setUser1Id(userId1);
                newConversation.setUser2Id(userId2);
                return conversationRepository.save(newConversation);
            });
    }

    @Override
    public ConversationEntity getConversationById(Integer conversationId) {
        return conversationRepository.findById(conversationId)
            .orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + conversationId));
    }

    @Override
    @Transactional
    public Collection<ConversationEntity> getAllUserRelatedDiscussions(String userId) {
        return conversationRepository.findUserRelatedConversations(userId);
    }
}
