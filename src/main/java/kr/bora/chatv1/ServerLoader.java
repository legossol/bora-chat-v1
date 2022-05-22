package kr.bora.chatv1;

import kr.bora.chatv1.common.ErrorConstant;
import kr.bora.chatv1.server.WebServer;
import kr.bora.chatv1.server.domain.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                NettyServer.getInstance().start();
            } catch (Exception e) {
                log.error(ErrorConstant.SERVER_CANNOT_STARTED.toString());
                e.printStackTrace();
            }
        }
    }
}
