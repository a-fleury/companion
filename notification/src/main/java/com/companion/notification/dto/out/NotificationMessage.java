package com.companion.notification.dto.out;

public record NotificationMessage(
    String value,
    String notificationType,
    MessageTarget messageTarget
    ) {}
