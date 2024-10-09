package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.Answer;
import team_project.clat.domain.Message;
import team_project.clat.dto.request.MessageAnswerReqDTO;
import team_project.clat.dto.request.MessageFileReqDTO;
import team_project.clat.dto.response.FileImageResDTO;
import team_project.clat.dto.response.MessageAnswerResDTO;
import team_project.clat.dto.response.MessageFileIncludedRespDTO;
import team_project.clat.dto.response.PageNationMessageResDTO;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.service.AnswerService;
import team_project.clat.service.MessageService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class MessageController {

    private final AnswerService answerService;

    private final JwtUtil jwtUtil;

    private final MessageService messageService;

    @GetMapping("/{chatRoomId}")
    public Slice<PageNationMessageResDTO> getMessages(@PathVariable Long chatRoomId, Pageable pageable) {
        return messageService.findByPageNationMessageList(chatRoomId, pageable);
    }


    @PostMapping("/answer")
    public MessageAnswerResDTO answerApi(@RequestBody MessageAnswerReqDTO messageAnswerDTO, HttpServletRequest request){
        log.info("답글 컨트롤러");
        String accessToken = request.getHeader("access");
        String username = jwtUtil.getUsername(accessToken);

        Answer answer = answerService.saveAnswer(messageAnswerDTO.getAnswer(), username, messageAnswerDTO.getMessageId());


        return new MessageAnswerResDTO(answer);
    }



    @PostMapping("/file")
    public MessageFileIncludedRespDTO test(@RequestBody MessageFileReqDTO messageFileRequest, HttpServletRequest request){
        String accessToken = request.getHeader("access");
        String username = jwtUtil.getUsername(accessToken);
        Long chatRoomId = messageFileRequest.getChatRoomId();

        List<FileImageResDTO> fileImageDTOList = messageFileRequest.getFileImageDTOList();

        Message message = messageService.saveFileMessage(username, chatRoomId, fileImageDTOList);

       return  new MessageFileIncludedRespDTO(message,fileImageDTOList);

    }





}
