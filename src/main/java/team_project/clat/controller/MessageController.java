package team_project.clat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import team_project.clat.domain.Dto.request.MessageRequestDto;
import team_project.clat.service.MessageService;

@Controller
@RequiredArgsConstructor

public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/chat/enter")
    public void enter(MessageRequestDto messageRequestDto){
        messageRequestDto.setMessage(messageRequestDto.getSenderName() + "님이 채팅방에 입장하셨습니다.");
        simpMessagingTemplate.convertAndSend("/sub/chat/" + messageRequestDto.getChatRoomId(), messageRequestDto);  // 해당 채팅방으로 메세지 전송

    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageRequestDto messageRequestDto){
        String senderName = messageRequestDto.getSenderName();
        Long chatRoomId = messageRequestDto.getChatRoomId();
        String message = messageRequestDto.getMessage();
        Long saveId = messageService.saveMessage(senderName, chatRoomId, message);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, message);


    }


}
