package kr.bora.chatv1.domain.reader;

import kr.legossol.borachat.common.DomainEvent.DomainEventType;
import kr.legossol.borachat.domain.model.entity.Message;
import kr.legossol.borachat.domain.reader.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.PostPersist;

public class MessageEventListener {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostPersist
    public void messagePostPersist(Message message) {
        eventPublisher.publishEvent(new MessageEvent(message, DomainEventType.CREATE,"topic","key"));
    }

}