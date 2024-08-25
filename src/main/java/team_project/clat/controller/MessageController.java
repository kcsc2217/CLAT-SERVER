package team_project.clat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import team_project.clat.domain.Dto.request.MessageRequestDto;
import team_project.clat.domain.Dto.response.MessageResponse;
import team_project.clat.domain.Message;
import team_project.clat.dto.FileImageDTO;
import team_project.clat.dto.MessageFileRequestDTO;
import team_project.clat.dto.MessageFileResponseDTO;
import team_project.clat.service.MessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "WebSocket Chat", description = "STOMP 기반의 WebSocket 채팅 API. 이 API는 WebSocket을 통해 실시간 채팅 기능을 제공합니다.")
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/chat/enter")
    @Operation(summary = "채팅방 입장", description = "사용자가 채팅방에 입장하면, STOMP 메시지를 통해 입장 메시지를 브로드캐스트합니다.")
    public void enter(MessageRequestDto messageRequestDto){
        messageRequestDto.setMessage(messageRequestDto.getSenderName() + "님이 채팅방에 입장하셨습니다.");
        simpMessagingTemplate.convertAndSend("/sub/chat/" + messageRequestDto.getCourseId(), messageRequestDto);  // 해당 채팅방으로 메세지 전송

    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageRequestDto messageRequestDto){ // 강의 아이디로 채팅 구독
        log.info("메세지가 수신됐습니다");
        String senderName = messageRequestDto.getSenderName();
        Long courseId = messageRequestDto.getCourseId();
        String message = messageRequestDto.getMessage();
        Message findMessage = messageService.saveMessage(senderName, courseId, message);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + courseId, new MessageResponse(findMessage));

    }

    @MessageMapping(value = "/chat/file")
    public void message(MessageFileRequestDTO messageFileRequest){
        log.info("파일이 전송되었습니다");
        String senderName = messageFileRequest.getSenderName();
        Long courseId = messageFileRequest.getCourseId();
        List<FileImageDTO> fileImageDTOList = messageFileRequest.getFileImageDTOList();

        Message message = messageService.saveFileMessage(senderName, courseId, fileImageDTOList);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + courseId, new MessageFileResponseDTO(message,fileImageDTOList));


    }


}
