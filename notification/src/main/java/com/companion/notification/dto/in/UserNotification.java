package com.companion.notification.dto.in;

import com.companion.notification.domain.NotificationType;

public record UserNotification(String user_id,String subject, String content, NotificationType type) {
}
