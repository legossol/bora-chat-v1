package kr.bora.chatv1.infrastrucure.listender;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

@Slf4j
public class MessageListener extends Thread{
    Socket socket = null;
    public MessageListener(Socket socket) {
        this.socket = socket;
    }

    public void messageThreadRun(){
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while(true){
                log.info(reader.readLine());
            }

        } catch (IOException e) {
            log.error("[messageThreadRun] : msg listener thread IOException");
            e.printStackTrace();
        } catch (Exception e){
            log.error("[messageThreadRun] : msg listener thread error");
            e.printStackTrace();
        }

    }

}