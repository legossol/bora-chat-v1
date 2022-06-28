package kr.bora.chatv1.domain.reader;

import kr.bora.chatv1.domain.reader.event.MessageEvent;
import kr.bora.chatv1.infrastrucure.producer.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
public class MessageSendHelperImpl {
//    private final MessageSender kafkaSender;

    @EventListener
    public void send(MessageEvent event) {
//        kafkaSender.send(event);
    }

}