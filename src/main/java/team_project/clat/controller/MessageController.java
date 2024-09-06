package team_project.clat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import team_project.clat.domain.Dto.request.MessageRequestDto;
import team_project.clat.domain.Dto.response.MessageResponse;
import team_project.clat.domain.Message;
import team_project.clat.dto.CustomUserDetails;
import team_project.clat.dto.FileImageDTO;
import team_project.clat.dto.MessageFileRequestDTO;
import team_project.clat.dto.MessageFileResponseDTO;
import team_project.clat.repository.MemberRepository;
import team_project.clat.service.MessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "WebSocket Chat", description = "STOMP 기반의 WebSocket 채팅 API. 이 API는 WebSocket을 통해 실시간 채팅 기능을 제공합니다.")
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MemberRepository memberRepository;

    @MessageMapping(value = "/chat/enter")
    @Operation(summary = "채팅방 입장", description = "사용자가 채팅방에 입장하면, STOMP 메시지를 통해 입장 메시지를 브로드캐스트합니다.")
    public void enter(MessageRequestDto messageRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
//        Member member = customUserDetails.getMember();
//        messageRequestDto.setMessage(member.getUsername() + "님이 채팅방에 입장하셨습니다.");
//        simpMessagingTemplate.convertAndSend("/sub/chat/" + messageRequestDto.getCourseId(), messageRequestDto);  // 해당 채팅방으로 메세지 전송

    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageRequestDto messageRequestDto, SimpMessageHeaderAccessor accessor){ // 강의 아이디로 채팅 구독
        log.info("메세지가 수신됐습니다");

        String username = (String) accessor.getSessionAttributes().get("username");

        Long chatRoomId = messageRequestDto.getChatRoomId();
        String message = messageRequestDto.getMessage();
        Message findMessage = messageService.saveMessage(username, chatRoomId, message);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, new MessageResponse(findMessage));

    }

    @MessageMapping(value = "/chat/file")
    public void messageFile(MessageFileRequestDTO messageFileRequest, SimpMessageHeaderAccessor accessor){
        log.info("파일이 전송되었습니다");
        String username = (String) accessor.getSessionAttributes().get("username");
        Long chatRoomId = messageFileRequest.getChatRoomId();
        List<FileImageDTO> fileImageDTOList = messageFileRequest.getFileImageDTOList();

        Message message = messageService.saveFileMessage(username, chatRoomId, fileImageDTOList); //파일 메세지 생성
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, new MessageFileResponseDTO(message,fileImageDTOList));


    }


}
