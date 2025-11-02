package com.companion.notification.events;

import org.springframework.context.ApplicationEvent;

import com.companion.notification.dto.in.UserMessage;

public class SendNotificationEvent extends ApplicationEvent{

    private final UserMessage message;

    public SendNotificationEvent(Object source, UserMessage message) {
        super(source);
        this.message = message;
    }

    public UserMessage getMessage() {
        return message;
    }
}
