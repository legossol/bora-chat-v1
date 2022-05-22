package kr.bora.chatv1.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public abstract class DomainEvent<T> {

    private T entity;
    private DomainEventType domainEventType;
    private String topic;
    private String partitionKey;
    private Integer partition;

    public DomainEvent(T source) {
        this.entity = source;
    }
    public T getSource() {
        return entity;
    }
    public static enum DomainEventType{
        CREATE("C"),
        UPDATE("U"),
        DELETE("D");

        private final String type;

        DomainEventType(String type) {
            this.type = type;
        }
    }

}
