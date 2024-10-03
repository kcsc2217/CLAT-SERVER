package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.Member;
import team_project.clat.domain.Memo;
import team_project.clat.domain.Message;
import team_project.clat.dto.request.MemoReqDTO;
import team_project.clat.dto.response.MemoResDTO;
import team_project.clat.dto.request.MessageReqDTO;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.MemoRepository;
import team_project.clat.service.MessageService;
import team_project.clat.service.TokenService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MessageService messageService;
    private final MemoRepository memoRepository;
    private final TokenService tokenService;

    @GetMapping("/{messageId}")
    public MemoResDTO memo(@PathVariable Long messageId, HttpServletRequest request) {
        Member findByMember = tokenService.getUsernameFromToken(request);

        Message message = messageService.findByWithMemo(messageId, findByMember);

        return new MemoResDTO(message);
    }


    @GetMapping("/findAll/{chatRoomId}")
    public List<MemoResDTO> memos(@PathVariable Long chatRoomId, HttpServletRequest request) {

        Member findByMember = tokenService.getUsernameFromToken(request);

        List<Message> messageList = messageService.findByWithChatRoomMemo(chatRoomId, findByMember.getId());

        return messageList.stream().map(message -> new MemoResDTO(message)).toList();

    }

    @PutMapping
    public MessageReqDTO.MemoUpdateReqDTO updateMemo(@RequestBody MemoReqDTO memoRequestDTO, HttpServletRequest request){

        Member findByMember = tokenService.getUsernameFromToken(request);

        Long id = messageService.updateMemo(memoRequestDTO.getMessageId(), memoRequestDTO.getMemoContent(), findByMember);

        Memo memo = memoRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 메모는 없습니다"));

        return new MessageReqDTO.MemoUpdateReqDTO(memo);
    }

}
