package com.companion.message.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sender_id", nullable = false)
    private String senderId;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "message_type", nullable = false)
    private String messageType;
    @Column(name = "readed", nullable = false)
    private boolean isRead;
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;
    
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private ConversationEntity conversation;
}
