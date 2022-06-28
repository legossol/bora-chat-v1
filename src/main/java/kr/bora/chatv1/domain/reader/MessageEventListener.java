package kr.bora.chatv1.domain.reader;

import kr.bora.chatv1.common.DomainEvent;
import kr.bora.chatv1.domain.model.entity.Message;
import kr.bora.chatv1.domain.reader.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.PostPersist;

public class MessageEventListener {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostPersist
    public void messagePostPersist(Message message) {
        eventPublisher.publishEvent(new MessageEvent(message, DomainEvent.DomainEventType.CREATE,"topic","key"));
    }

}