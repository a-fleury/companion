package com.companion.message.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationDTO {
 
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_1")
    private UserDTO user1;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_2")
    private UserDTO user2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("last_message")
    private MessageDTO lastMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("contact_image_url")
    private String contactImageUrl;
}
