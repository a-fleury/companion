package com.companion.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.companion.message.dto.out.ConversationDTO;
import com.companion.message.dto.out.UserDTO;
import com.companion.message.entity.ConversationEntity;

@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface ConversationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user1Id", target = "user1", qualifiedByName = "idToUser")
    @Mapping(source = "user2Id", target = "user2", qualifiedByName = "idToUser")
    @Mapping(source = "lastMessage", target = "lastMessage")
    @Mapping(source = "contactImageUrl", target = "contactImageUrl")
    ConversationDTO toDto(ConversationEntity conversation);

    @Named("idToUser")
    default UserDTO idToUser(String id) {
        return id == null ? null : new UserDTO(id, null);
    }
    
}
