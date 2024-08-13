package team_project.clat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Dto.request.ChatRoomCreateDto;
import team_project.clat.domain.Dto.response.CreateMemberResponse;
import team_project.clat.domain.Dto.response.MessageResponse;
import team_project.clat.service.ChatRoomService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatRoom")
@Tag(name = "ChatRoom", description = "ChatRoomApi")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;


    @PostMapping(value = "/create",   consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "create ChatRoom", description = "강의아이디로 채팅방을 만든다.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "성공"),
    @ApiResponse(responseCode = "404", description = "해당 강의실 아이디가 존재 하지 않습니다")}

    )
    public CreateMemberResponse createChatRoom(@RequestBody @Valid ChatRoomCreateDto chatRoomCreateDto) {

        log.info("id ={}", chatRoomCreateDto.getCourseId());

        Long saveId = chatRoomService.save(chatRoomCreateDto.getRoomName(), chatRoomCreateDto.getCourseId());

        log.info("채팅방 생성완료");

        return new CreateMemberResponse(saveId);
    }

    @GetMapping(value = "/messages/{chatRoomId}")
    @Operation(summary = "select List Message", description = "채팅 메세지 조회")
    public List<MessageResponse> getMessage(@PathVariable Long chatRoomId) {
        log.info("전체 메세지 조회");
        ChatRoom chatRoom = chatRoomService.findFetchMessageById(chatRoomId);

        List<MessageResponse> list = chatRoom.getMessageList().stream().map(message -> new MessageResponse(message.getSenderName(), message.getMessage(), message.getCreatedDate()))
                .toList();
        return list;

    }




}
