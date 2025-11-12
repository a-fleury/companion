package com.companion.message.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.companion.message.entity.ConversationEntity;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
    
    Optional<ConversationEntity> findById(Integer id);

    @Query("""
        SELECT c FROM ConversationEntity c
        WHERE (c.user1Id = :user1Id AND c.user2Id = :user2Id)
           OR (c.user1Id = :user2Id AND c.user2Id = :user1Id)
    """)
    Optional<ConversationEntity> findConversationBetweenUsers(
        @Param("user1Id") String user1Id,
        @Param("user2Id") String user2Id);


    @Query("""
        SELECT c FROM ConversationEntity c
        WHERE c.user1Id = :userId OR c.user2Id = :userId
    """)
    Collection<ConversationEntity> findUserRelatedConversations(@Param("userId") String userId);
}
