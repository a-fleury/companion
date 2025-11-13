package com.companion.notification.events;

import org.springframework.context.ApplicationEvent;

import com.companion.notification.dto.in.UserNotification;

import lombok.Getter;

@Getter
public class SendNotificationEvent extends ApplicationEvent{

    private final UserNotification message;

    public SendNotificationEvent(Object source, UserNotification message) {
        super(source);
        this.message = message;
    }

    public UserNotification getMessage() {
        return message;
    }
}
