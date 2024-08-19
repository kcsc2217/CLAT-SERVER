package team_project.clat.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import team_project.clat.domain.Dto.request.MessageRequestDto;
import team_project.clat.domain.Dto.response.MessageResponse;
import team_project.clat.domain.Message;
import team_project.clat.service.MessageService;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "WebSocket Chat", description = "WebSocket 기반 채팅 API")
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/chat/enter")
    public void enter(MessageRequestDto messageRequestDto){
        messageRequestDto.setMessage(messageRequestDto.getSenderName() + "님이 채팅방에 입장하셨습니다.");
        simpMessagingTemplate.convertAndSend("/sub/chat/" + messageRequestDto.getCourseId(), messageRequestDto);  // 해당 채팅방으로 메세지 전송

    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageRequestDto messageRequestDto){
        log.info("메세지가 수신됐습니다");
        String senderName = messageRequestDto.getSenderName();
        Long courseId = messageRequestDto.getCourseId();
        String message = messageRequestDto.getMessage();
        Message findMessage = messageService.saveMessage(senderName, courseId, message);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + courseId, new MessageResponse(findMessage));

    }


}
