package kr.bora.chatv1.domain.reader.event;

import kr.bora.chatv1.common.DomainEvent;
import kr.bora.chatv1.domain.model.entity.Message;
import lombok.Getter;

@Getter
public class MessageEvent extends DomainEvent<Message> {

    public MessageEvent(Message message, DomainEvent.DomainEventType domainEventType, String topic,
                        String partitionKey) {
        this.setEntity(message);
        this.setDomainEventType(domainEventType);
        this.setTopic(topic);
        this.setPartitionKey(partitionKey);
    }
}