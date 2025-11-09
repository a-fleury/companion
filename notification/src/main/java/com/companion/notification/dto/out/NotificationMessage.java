package com.companion.notification.dto.out;

public record NotificationMessage(
    String subject,
    String value,
    String notificationType,
    MessageTarget messageTarget
    ) {}
