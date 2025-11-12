package com.companion.notification.notifier.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.companion.notification.notifier.contract.Notifier;
import com.companion.notification.notifier.domain.Notification;
import com.companion.notification.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EmailNotifier implements Notifier {

    private final JavaMailSender mailSender;
    private final UserService userService;

    @Override
    public void sendNotification(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("app.companion.mail@gmail.com");
        message.setTo(userService.getUserEmailById(notification.userId()));
        message.setText(notification.content());
        message.setSubject(notification.subject());
        mailSender.send(message);
        log.info("Sending email notification to: " + notification.userId());
    }
    
}
