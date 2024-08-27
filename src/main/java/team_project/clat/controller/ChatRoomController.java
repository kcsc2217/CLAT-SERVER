package team_project.clat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Dto.request.ChatRoomCreateDto;
import team_project.clat.domain.Dto.response.CreateMemberResponse;
import team_project.clat.domain.Dto.response.MessageResponse;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.ChatRoomMessageDTO;
import team_project.clat.dto.MessageIncludeFileDTO;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.ChatRoomRepository;
import team_project.clat.repository.MessageRepository;
import team_project.clat.service.ChatRoomService;
import team_project.clat.service.CourseService;
import team_project.clat.service.TokenService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatRoom")
@Tag(name = "ChatRoom", description = "ChatRoomApi") 
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;
    private final TokenService tokenService;
    private final CourseService courseService;


    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/{courseId}")
    @Operation(summary = "select List Message", description = "채팅 메세지 조회")
    public List<MessageResponse> getMessage(@PathVariable Long courseId) {
        log.info("전체 메세지 조회");
        ChatRoom chatRoom = chatRoomService.findFetchMessageCourseById(courseId);

        List<MessageResponse> list = chatRoom.getMessageList().stream().map(message -> new MessageResponse(message.getSenderName(), message.getMessage(), message.getCreatedDate()))
                .toList();
        return list;

    }

    @GetMapping("/chat/{chatRoomId}") // 채팅방 이름과 채팅방 메세지 조회
    public ChatRoomMessageDTO getFileMessage(@PathVariable Long chatRoomId, HttpServletRequest request){
        Member findMember = tokenService.getUsernameFromToken(request);

        validationCourse(chatRoomId, findMember);
        ChatRoom chatRoom = chatRoomRepository.findFetchByCourseAndMessage(chatRoomId).orElseThrow(() -> new NotFoundException("해당 채팅방 없습니다"));

        return new ChatRoomMessageDTO(chatRoom);
    }

    private void validationCourse(Long chatRoomId, Member findMember) {
        boolean flag = courseService.existUseMemberCourse(findMember, chatRoomId);

        if(!flag){
            throw new NotFoundException("해당 학생은 해당 강의를 듣지 않습니다");
        }
    }


}
