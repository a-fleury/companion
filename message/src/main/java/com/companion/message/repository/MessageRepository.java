package com.companion.message.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.companion.message.entity.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    Collection<MessageEntity> findByConversationIdAndIsReadFalse(Integer conversationId); 
}
