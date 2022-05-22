package kr.bora.chatv1.domain.reader.event;

import kr.legossol.borachat.common.DomainEvent;
import kr.legossol.borachat.domain.model.entity.Message;
import lombok.Getter;

@Getter
public class MessageEvent extends DomainEvent<Message> {

    public MessageEvent(Message message, DomainEventType domainEventType, String topic,
        String partitionKey) {
        this.setEntity(message);
        this.setDomainEventType(domainEventType);
        this.setTopic(topic);
        this.setPartitionKey(partitionKey);
    }
}