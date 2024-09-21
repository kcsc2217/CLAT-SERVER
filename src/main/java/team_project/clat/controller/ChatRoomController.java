package team_project.clat.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Dto.request.ChatRoomCreateDto;
import team_project.clat.domain.Dto.response.CreateMemberResponse;
import team_project.clat.domain.Member;
import team_project.clat.dto.ChatRoomMessageDTO;
import team_project.clat.dto.RoomKeyReq;
import team_project.clat.dto.RoomKeyRes;
import team_project.clat.exception.NotFoundException;
import team_project.clat.service.ChatRoomService;
import team_project.clat.service.CourseService;
import team_project.clat.service.TokenService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatRoom")
@Tag(name = "ChatRoom", description = "ChatRoomApi") 
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    private final TokenService tokenService;
    private final CourseService courseService;


    @PostMapping(value = "")
    public CreateMemberResponse createChatRoom(@RequestBody @Valid ChatRoomCreateDto chatRoomCreateDto) {

        chatRoomService.roomSaveValidation(chatRoomCreateDto.getCourseId(), chatRoomCreateDto.getWeek()); // 똑같은 주차의 강의가 있는지 검증 로직 있으면 예외 처리
        ChatRoom findChatRoom = chatRoomService.save(chatRoomCreateDto);


        log.info("채팅방 생성완료");

        return new CreateMemberResponse(findChatRoom);
    }

//    @GetMapping(value = "/{courseId}")
//    @Operation(summary = "select List Message", description = "채팅 메세지 조회")
//    public List<MessageResponse> getMessage(@PathVariable Long courseId) {
//        log.info("전체 메세지 조회");
//        ChatRoom chatRoom = chatRoomService.findFetchMessageCourseById(courseId);
//
//        List<MessageResponse> list = chatRoom.getMessageList().stream().map(message -> new MessageResponse(message.getSenderName(), message.getMessage(), message.getCreatedDate()))
//                .toList();
//        return list;
//
//    }

    @GetMapping("/{chatRoomId}") // 채팅방 이름과 채팅방 메세지 조회
    public ChatRoomMessageDTO getFileMessage(@PathVariable Long chatRoomId ){

        ChatRoom chatRoom = chatRoomService.findFetchMessageAndImage(chatRoomId);

        return new ChatRoomMessageDTO(chatRoom);
    }

    // 채팅방 검증 로직
    @PostMapping("/validation")
    public RoomKeyRes validationRoom(@RequestBody RoomKeyReq roomKeyReq){

       // 현재 유저가 해당 강의를 듣고 있는지 검증하기 위해
        boolean flag = chatRoomService.validationRoom(roomKeyReq);

        return new RoomKeyRes(flag);
    }


//    private void validationCourse(Long chatRoomId, HttpServletRequest request) {
//        boolean flag = courseService.existUseMemberCourse(request, chatRoomId);
//
//        if(!flag){
//            throw new NotFoundException("해당 학생은 해당 강의를 듣지 않습니다");
//        }
//    }


}
