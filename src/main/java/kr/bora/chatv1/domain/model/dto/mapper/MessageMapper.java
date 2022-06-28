package kr.bora.chatv1.domain.model.dto.mapper;

import kr.bora.chatv1.common.GenericMapper;
import kr.bora.chatv1.domain.model.dto.MessageDto;
import kr.bora.chatv1.domain.model.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends GenericMapper<MessageDto, Message> {
}
