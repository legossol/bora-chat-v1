package kr.bora.chatv1.domain.reader;

import kr.legossol.borachat.domain.reader.event.MessageEvent;
import kr.legossol.borachat.infrastrucure.producer.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSendHelperImpl {
    private final MessageSender kafkaSender;

    @EventListener
    public void send(MessageEvent event) {
//        kafkaSender.send(event);
    }

}