package com.companion.message.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationDTO {
 
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDTO user_1;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDTO user_2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MessageDTO last_message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contact_image_url;
}
