package com.companion.message.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companion.message.service.contract.MessageService;
import com.companion.message.service.contract.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("{conversationId}/unread-messages")
@RequiredArgsConstructor
public class UnreadMessageController {
    
    private final MessageService messageService;
    private final UserService userService;

    @PatchMapping
    public ResponseEntity<Void> markAsRead(@PathVariable Integer conversationId) {
        this.messageService.markMessageAsReadFromUser(conversationId, userService.getConnectedUserId());
        return ResponseEntity.ok().build();
    }
}
