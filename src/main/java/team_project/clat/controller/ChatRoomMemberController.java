package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.Member;
import team_project.clat.dto.response.ChatRoomMemberResDTO;
import team_project.clat.repository.ChatRoomMemberRepository;
import team_project.clat.service.TokenService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatRoomMember")
public class ChatRoomMemberController {

    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final TokenService tokenService;



    @GetMapping("/{chatRoomId}")
    public ChatRoomMemberResDTO checkChatRoomMember(@PathVariable("chatRoomId") Long chatRoomId, HttpServletRequest request) {

        Member usernameFromToken = tokenService.getUsernameFromToken(request);

        boolean flag = chatRoomMemberRepository.existsByChatRoomIdAndMemberId(chatRoomId, usernameFromToken.getId());

        return new ChatRoomMemberResDTO(flag);
    }


}
