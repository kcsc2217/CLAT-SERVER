package team_project.clat.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.ChatRoom;
import team_project.clat.dto.request.ChatRoomCreateReqDTO;
import team_project.clat.dto.response.CreateMemberResponse;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.response.ChatRoomInformationResDTO;
import team_project.clat.dto.response.ChatRoomMessageResDTO;
import team_project.clat.dto.request.RoomKeyReqDTO;
import team_project.clat.dto.response.RoomKeyResDTO;
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

    @PostMapping(value = "") //채팅방 생성
    public CreateMemberResponse createChatRoom(@RequestBody @Valid ChatRoomCreateReqDTO chatRoomCreateDto) {

        chatRoomService.roomSaveValidation(chatRoomCreateDto.getCourseId(), chatRoomCreateDto.getWeek()); // 똑같은 주차의 강의가 있는지 검증 로직 있으면 예외 처리
        ChatRoom findChatRoom = chatRoomService.save(chatRoomCreateDto);

        log.info("채팅방 생성완료");

        return new CreateMemberResponse(findChatRoom);
    }

    @GetMapping("/{chatRoomId}") // 채팅방 이름과 채팅방 메세지 조회
    public ChatRoomMessageResDTO getFileMessage(@PathVariable Long chatRoomId ){

        return chatRoomService.findFetchMessageAndImage(chatRoomId);

    }

    @GetMapping("/subQuery/{chatRoomId}")
    public ChatRoomMessageResDTO getSubQueryMessage(@PathVariable Long chatRoomId ){

        List<Message> subQueryFetchMessageAndImage = messageService.findSubQueryFetchMessageAndImage(chatRoomId);

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();

        return new ChatRoomMessageResDTO(chatRoom);
    }

    @GetMapping("/queryUpdate/{chatRoomId}")
    public ChatRoomMessageResDTO queryUpdateMessage(@PathVariable Long chatRoomId ){

       return  messageService.findUpgradeQueryByFetchMessageAndImage(chatRoomId);
    }

    // 채팅방 검증 로직
    @PostMapping("/validation")
    public RoomKeyResDTO validationRoom(@RequestBody RoomKeyReqDTO roomKeyReq, HttpServletRequest request){

        // 현재 유저가 해당 강의를 듣고 있는지 검증하기 위해
        boolean flag = chatRoomService.validationRoom(roomKeyReq);

        saveChatRoomMember(roomKeyReq, request, flag);

        return new RoomKeyResDTO(flag);
    }



    @GetMapping("/api/{chatRoomId}")
    public ChatRoomInformationResDTO getChatRoom(@PathVariable("chatRoomId") Long chatRoomId){
        return chatRoomService.getRoom(chatRoomId);
    }

    private void saveChatRoomMember(RoomKeyReqDTO roomKeyReq, HttpServletRequest request, boolean flag) {
        if(flag){ // 채팅방에 대한
            Member findByMember = tokenService.getUsernameFromToken(request);
            chatRoomMemberService.saveChatRoomMember(roomKeyReq, findByMember);
        }
    }




}
