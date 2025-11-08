package com.companion.message.entity;

import java.util.Collection;
import java.util.Comparator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "conversation")
public class ConversationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user1_id", nullable = false)
    private String user1Id;
    @Column(name = "user2_id", nullable = false)
    private String user2Id;
    @OneToMany(mappedBy = "conversation")
    private Collection<MessageEntity> messages;

    public MessageEntity getLastMessage () {
        return this.messages.stream()
                .max(Comparator.comparing(MessageEntity::getSentAt))
                .orElse(null);
    }

    public String getContactImageUrl() {
        // Placeholder for actual implementation to retrieve contact image URL
        return "https://www.google.com/imgres?q=christophe%20fiorio&imgurl=https%3A%2F%2Fpbs.twimg.com%2Fprofile_images%2F2162998673%2Fimage_400x400.jpg&imgrefurl=https%3A%2F%2Fx.com%2Fchfiorio&docid=cukNGWcduCWhyM&tbnid=jUBRdkAx81xuWM&vet=12ahUKEwiOyPfZtbeQAxVxRqQEHbvBG-cQM3oECBcQAA..i&w=400&h=400&hcb=2&ved=2ahUKEwiOyPfZtbeQAxVxRqQEHbvBG-cQM3oECBcQAA";
    }
}
