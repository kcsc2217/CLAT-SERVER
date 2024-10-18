package team_project.clat.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import team_project.clat.domain.Answer;
import team_project.clat.domain.Message;
import team_project.clat.dto.request.MessageAnswerReqDTO;
import team_project.clat.dto.request.MessageFileReqDTO;
import team_project.clat.dto.request.MessageReqDTO;
import team_project.clat.dto.response.FileImageResDTO;
import team_project.clat.dto.response.MessageAnswerResDTO;
import team_project.clat.dto.response.MessageFileIncludedRespDTO;
import team_project.clat.dto.response.MessageResDTO;
import team_project.clat.service.AnswerService;
import team_project.clat.service.MessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "WebSocket Chat", description = "STOMP 기반의 WebSocket 채팅 API. 이 API는 WebSocket을 통해 실시간 채팅 기능을 제공합니다.")
public class ChatController {
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AnswerService answerService;

    @MessageMapping(value = "/chat/message")
    public void message(MessageReqDTO messageRequestDto, SimpMessageHeaderAccessor accessor){ // 강의 아이디로 채팅 구독
        log.info("메세지가 수신됐습니다");

        String username = (String) accessor.getSessionAttributes().get("username");

        Long chatRoomId = messageRequestDto.getChatRoomId();
        String message = messageRequestDto.getMessage();
        Message findMessage = messageService.saveMessage(username, chatRoomId, message);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, new MessageResDTO(findMessage));

    }

        @MessageMapping(value = "/chat/file")
    public void messageFile(MessageFileReqDTO messageFileRequest, SimpMessageHeaderAccessor accessor){
        log.info("파일이 전송되었습니다");
        String username = (String) accessor.getSessionAttributes().get("username");
        Long chatRoomId = messageFileRequest.getChatRoomId();
        List<FileImageResDTO> fileImageDTOList = messageFileRequest.getFileImageDTOList();

        Message message = messageService.saveFileMessage(username, chatRoomId, fileImageDTOList); //파일 메세지 생성
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, new MessageFileIncludedRespDTO(message,fileImageDTOList));

    }

    @MessageMapping(value = "/chat/answer")
    public void messageAnswer(MessageAnswerReqDTO messageAnswerDTO, SimpMessageHeaderAccessor accessor){
        log.info("답글이 전송되었습니다");
        String username = (String) accessor.getSessionAttributes().get("username");

        Answer answer = answerService.saveAnswer(messageAnswerDTO.getAnswer(), username, messageAnswerDTO.getMessageId());

       simpMessagingTemplate.convertAndSend("/sub/chat/" + messageAnswerDTO.getChatRoomId(), new MessageAnswerResDTO(answer) );


    }


}
