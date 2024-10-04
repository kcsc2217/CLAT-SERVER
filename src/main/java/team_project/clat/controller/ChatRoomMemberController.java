package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.domain.Member;
import team_project.clat.dto.response.ChatRoomMemberResDTO;
import team_project.clat.service.ChatRoomMemberService;
import team_project.clat.service.TokenService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatRoomMember")
public class ChatRoomMemberController {

    private final ChatRoomMemberService chatRoomMemberService;
    private final TokenService tokenService;



    @GetMapping("/{chatRoomId}")
    public ChatRoomMemberResDTO checkChatRoomMember(@PathVariable("chatRoomId") Long chatRoomId, HttpServletRequest request) {

        Member findByMember = tokenService.getUsernameFromToken(request);

       return chatRoomMemberService.existMemberInChatRoom(chatRoomId, findByMember.getId());
    }


}
