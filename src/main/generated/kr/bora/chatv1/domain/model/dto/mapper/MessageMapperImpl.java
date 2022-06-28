package kr.bora.chatv1.domain.model.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.bora.chatv1.domain.model.dto.MessageDto;
import kr.bora.chatv1.domain.model.dto.MessageDto.MessageDtoBuilder;
import kr.bora.chatv1.domain.model.entity.Message;
import kr.bora.chatv1.domain.model.entity.Message.MessageBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-04T16:45:32+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MessageDtoBuilder messageDto = MessageDto.builder();

        messageDto.id( arg0.getId() );
        messageDto.message( arg0.getMessage() );

        return messageDto.build();
    }

    @Override
    public Message toEntity(MessageDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MessageBuilder message = Message.builder();

        message.id( arg0.getId() );
        message.message( arg0.getMessage() );

        return message.build();
    }

    @Override
    public List<MessageDto> toDtoList(List<Message> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( arg0.size() );
        for ( Message message : arg0 ) {
            list.add( toDto( message ) );
        }

        return list;
    }

    @Override
    public List<Message> toEntityList(List<MessageDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Message> list = new ArrayList<Message>( arg0.size() );
        for ( MessageDto messageDto : arg0 ) {
            list.add( toEntity( messageDto ) );
        }

        return list;
    }
}
