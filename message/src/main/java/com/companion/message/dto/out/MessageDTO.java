package com.companion.message.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    
    private Integer id;
    private String sender_id;
    private String content;
    private String message_type;
    private Boolean is_read;
    private String send_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modified_at;
}
