package com.companion.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.companion.message.dto.out.MessageDTO;
import com.companion.message.entity.MessageEntity;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "read", target = "is_read")
    @Mapping(source = "messageType", target = "message_type")
    @Mapping(source = "senderId", target = "sender_id")
    @Mapping(source = "sentAt" , target = "send_at", dateFormat = "yyyy-MM-dd'T'HH:mm")
    @Mapping(source = "modifiedAt" , target = "modified_at", dateFormat = "yyyy-MM-dd'T'HH:mm")
    MessageDTO toDTO(MessageEntity message);
}
