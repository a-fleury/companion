package com.companion.message.service.contract;

import java.util.Collection;

import com.companion.message.entity.ConversationEntity;

public interface ConversationService {
    
    ConversationEntity getOrStartConversation(String userId1, String userId2);

    ConversationEntity getConversationById(Integer conversationId);

    Collection<ConversationEntity> getAllUserRelatedDiscussions(String userId);
}
