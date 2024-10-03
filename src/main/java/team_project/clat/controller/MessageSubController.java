package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.domain.Answer;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.request.MessageAnswerReqDTO;
import team_project.clat.dto.request.MessageFileReqDTO;
import team_project.clat.dto.request.MessageMemoReqDTO;
import team_project.clat.dto.request.MessageReqDTO;
import team_project.clat.dto.response.FileImageResDTO;
import team_project.clat.dto.response.MessageAnswerResDTO;
import team_project.clat.dto.response.MessageFileIncludedRespDTO;
import team_project.clat.dto.response.MessageMemoResDTO;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.service.AnswerService;
import team_project.clat.service.MessageService;
import team_project.clat.service.TokenService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageSubController {

    private final AnswerService answerService;

    private final JwtUtil jwtUtil;

    private final MessageService messageService;
    private final TokenService tokenService;


    @PostMapping("/chat/answer")
    public MessageAnswerResDTO answerApi(@RequestBody MessageAnswerReqDTO messageAnswerDTO, HttpServletRequest request){
        log.info("답글 컨트롤러");
        String accessToken = request.getHeader("access");
        String username = jwtUtil.getUsername(accessToken);

        Answer answer = answerService.saveAnswer(messageAnswerDTO.getAnswer(), username, messageAnswerDTO.getMessageId());


        return new MessageAnswerResDTO(answer);
    }



    @PostMapping("/chat/file")
    public MessageFileIncludedRespDTO test(@RequestBody MessageFileReqDTO messageFileRequest, HttpServletRequest request){
        String accessToken = request.getHeader("access");
        String username = jwtUtil.getUsername(accessToken);
        Long chatRoomId = messageFileRequest.getChatRoomId();

        List<FileImageResDTO> fileImageDTOList = messageFileRequest.getFileImageDTOList();

        Message message = messageService.saveFileMessage(username, chatRoomId, fileImageDTOList);

       return  new MessageFileIncludedRespDTO(message,fileImageDTOList);

    }

    @PostMapping("/chat/memo")
    public MessageMemoResDTO test2(@RequestBody MessageMemoReqDTO messageMemoRequestDTO, HttpServletRequest request){
        Member findMember = tokenService.getUsernameFromToken(request);

        Message message = messageService.saveMemo(messageMemoRequestDTO, findMember.getId());

        return new MessageMemoResDTO(message);

    }

}
