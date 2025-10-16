package com.companion.message.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.companion.message.dto.out.MessageDTO;
import com.companion.message.entity.ConversationEntity;
import com.companion.message.mapper.MessageMapper;
import com.companion.message.service.contract.ConversationService;
import com.companion.message.service.contract.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;
    private final UserService userService;
    private final MessageMapper messageMapper;

    
    @GetMapping("/{conversationId}")
    @Transactional
    public ResponseEntity<Collection<MessageDTO>> getConversationMessages(@PathVariable("conversationId") Integer conversationId) {
        ConversationEntity conversation = this.conversationService.getConversationById(conversationId);
        return ResponseEntity.ok().body(conversation.getMessages().stream().map(messageMapper::toDTO).toList());
    }

        @GetMapping
    public ResponseEntity<Collection<Integer>> getAllUserRelatedDiscussions() {
        Collection<ConversationEntity> discussions = this.conversationService.getAllUserRelatedDiscussions(userService.getConnectedUserId());
        return ResponseEntity.ok(discussions.stream().map(ConversationEntity::getId).toList());
    }

}
