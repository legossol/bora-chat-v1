package kr.bora.chatv1.infrastrucure.client;

import kr.bora.chatv1.infrastrucure.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/bora/chat")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/joinchat")
    public void joinChat(){
        clientService.joinChatRoom();
    }


}
