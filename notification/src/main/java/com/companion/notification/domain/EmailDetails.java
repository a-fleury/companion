package com.companion.notification.domain;

public record EmailDetails (
    String recipient,
    String msgBody,
    String subject,
    String attachment
) {}
