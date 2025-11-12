package com.companion.message.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companion.message.dto.in.CreateMessageRequest;
import com.companion.message.service.contract.MessageService;
import com.companion.message.service.contract.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
 
    private final UserService userService;
    private final MessageService messageService;

    /**
     * Store a message sent from the connected user to another user identified by his id
     * @param message
     */
    @PostMapping
    public void storeMessage(@RequestBody CreateMessageRequest message) {
        String senderId = userService.getConnectedUserId();
        messageService.storeMessage(message.getMessage(),"user", senderId, message.getReceiverId());
    }
}
