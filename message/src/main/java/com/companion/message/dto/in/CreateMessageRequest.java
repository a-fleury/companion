package com.companion.message.dto.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMessageRequest {

    private String message;

    private String receiverId;

    public CreateMessageRequest(String message, String receiverId) {
        this.message = message;
        this.receiverId = receiverId;
    }
}
