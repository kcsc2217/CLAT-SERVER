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
import team_project.clat.domain.Message;
import team_project.clat.dto.ChatRoomInformationDTO;
import team_project.clat.dto.ChatRoomMessageDTO;
import team_project.clat.dto.RoomKeyReq;
import team_project.clat.dto.RoomKeyRes;
import team_project.clat.repository.ChatRoomRepository;
import team_project.clat.service.ChatRoomMemberService;
import team_project.clat.service.ChatRoomService;
import team_project.clat.service.MessageService;
import team_project.clat.service.TokenService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatRoom")
@Tag(name = "ChatRoom", description = "ChatRoomApi") 
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    private final ChatRoomMemberService chatRoomMemberService;

    private final MessageService messageService;


    private final TokenService tokenService;

    private final ChatRoomRepository chatRoomRepository;

    @PostMapping(value = "")
    public CreateMemberResponse createChatRoom(@RequestBody @Valid ChatRoomCreateDto chatRoomCreateDto) {

        chatRoomService.roomSaveValidation(chatRoomCreateDto.getCourseId(), chatRoomCreateDto.getWeek()); // 똑같은 주차의 강의가 있는지 검증 로직 있으면 예외 처리
        ChatRoom findChatRoom = chatRoomService.save(chatRoomCreateDto);


        log.info("채팅방 생성완료");

        return new CreateMemberResponse(findChatRoom);
    }

    @GetMapping("/{chatRoomId}") // 채팅방 이름과 채팅방 메세지 조회
    public ChatRoomMessageDTO getFileMessage(@PathVariable Long chatRoomId ){

        ChatRoom chatRoom = chatRoomService.findFetchMessageAndImage(chatRoomId);

        return new ChatRoomMessageDTO(chatRoom);
    }

    @GetMapping("/subQuery/{chatRoomId}")
    public ChatRoomMessageDTO getSubQueryMessage(@PathVariable Long chatRoomId ){

        List<Message> subQueryFetchMessageAndImage = messageService.findSubQueryFetchMessageAndImage(chatRoomId);

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();

        return new ChatRoomMessageDTO(chatRoom);
    }

    @GetMapping("/queryUpdate/{chatRoomId}")
    public ChatRoomMessageDTO queryUpdateMessage(@PathVariable Long chatRoomId ){

       return  messageService.findUpgradeQueryByFetchMessageAndImage(chatRoomId);
    }

    // 채팅방 검증 로직
    @PostMapping("/validation")
    public RoomKeyRes validationRoom(@RequestBody RoomKeyReq roomKeyReq, HttpServletRequest request){

        // 현재 유저가 해당 강의를 듣고 있는지 검증하기 위해
        boolean flag = chatRoomService.validationRoom(roomKeyReq);

        if(flag){
            Member findByMember = tokenService.getUsernameFromToken(request);
          chatRoomMemberService.saveChatRoomMember(roomKeyReq, findByMember);
        }

        return new RoomKeyRes(flag);
    }

    @GetMapping("/api/{chatRoomId}")
    public ChatRoomInformationDTO getChatRoom(@PathVariable("chatRoomId") Long chatRoomId){
        ChatRoom room = chatRoomService.getRoom(chatRoomId);
        return new ChatRoomInformationDTO(room);

    }




}
