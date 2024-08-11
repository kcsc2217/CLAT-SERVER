package team_project.clat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.domain.Dto.request.ChatRoomCreateDto;
import team_project.clat.service.ChatRoomService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;


    @PostMapping("/chat/create")
    public void createChatRoom(@RequestBody @Valid ChatRoomCreateDto chatRoomCreateDto) {

        
    }



}
