package kr.bora.chatv1.infrastrucure.client.service;

import kr.bora.chatv1.infrastrucure.listender.MessageListener;
import kr.bora.chatv1.infrastrucure.producer.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{
    @Override
    public void joinChatRoom() {
        try {
            Socket socket = new Socket("localhost",8888);
            log.info("user trying join chat room TO host : " + socket.getInetAddress().getHostAddress());
            MessageListener thread1 = new MessageListener(socket);
            MessageSender thread2 = new MessageSender(socket);

            thread1.start();
            thread2.start();

        }catch (UnknownHostException e){
            log.error("[joinChatRoom] : ip address is wrong");
            e.printStackTrace();
        }
        catch (IOException e) {
            log.error("[joinChatRoom] : IOE exceptiong");
            e.printStackTrace();
        }

    }
}
