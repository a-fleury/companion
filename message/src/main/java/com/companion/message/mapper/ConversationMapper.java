package com.companion.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.companion.message.dto.out.ConversationDTO;
import com.companion.message.entity.ConversationEntity;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

    @Mapping(source = "id", target = "id")
    ConversationDTO toDto(ConversationEntity conversation);
}
