package kr.bora.chatv1.infrastrucure.producer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class MessageSender extends Thread{
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);

    public MessageSender(Socket socket) {
        this.socket = socket;
    }

    public void writeThreadRun(){
        try{
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream,true);
            while (true){
                writer.println(scanner.nextLine());

            }
        }catch (IOException e){
            log.error("[writeThreadRun] : msg write thread IOException");
            e.printStackTrace();
        }
        catch (Exception e){
            log.error("[writeThreadRun] : msg write thread error");
            e.printStackTrace();
        }
    }
}
