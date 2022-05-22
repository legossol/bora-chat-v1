package kr.bora.chatv1.domain.model.dto.mapper;

import kr.legossol.borachat.common.GenericMapper;
import kr.legossol.borachat.domain.model.dto.MessageDto;
import kr.legossol.borachat.domain.model.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends GenericMapper<MessageDto, Message> {
}
