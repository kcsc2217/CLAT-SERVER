package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Course;
import team_project.clat.dto.Dto.request.ChatRoomCreateDto;
import team_project.clat.domain.Message;
import team_project.clat.dto.RoomKeyReq;
import team_project.clat.exception.DuplicateCourseChatRoomException;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.ChatRoomRepository;
import team_project.clat.repository.CourseRepository;
import team_project.clat.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final CourseRepository courseRepository;
    private final MessageRepository messageRepository;


    @Transactional
    public ChatRoom save(ChatRoomCreateDto chatRoomCreateDto) {

        Course findCourse = courseRepository.findById(chatRoomCreateDto.getCourseId()).orElseThrow(() -> new NotFoundException("Course not found")); //강의실 아이디찾기

        log.info("수업 찾기 완료");
        String roomName = chatRoomCreateDto.getRoomName();
        int week = chatRoomCreateDto.getWeek();

        ChatRoom chatRoom = new ChatRoom(roomName, week,  findCourse); // 객체를 생성하면서 연관관계 매핑

        ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);
        log.info("채팅 생성");
        return saveChatRoom;
    }


    public ChatRoom findFetchMessageAndImage(Long chatRoomId){
        ChatRoom chatRoom = chatRoomRepository.findFetchByChatRoom(chatRoomId).orElseThrow(() -> new NotFoundException("해당 채팅방은 없습니다"));

        List<Long> messageIds = getChatRoomMessageIds(chatRoom);

        messageRepository.findAllMessageIds(messageIds); // n+1 문제를 해결하기 위한 fetchjoin

        return  chatRoom;

    }






    private static List<Long> getChatRoomMessageIds(ChatRoom chatRoom) {
        List<Long> messageIds = chatRoom.getMessageList().stream().map(Message::getId).collect(Collectors.toList());
        return messageIds;
    }


    // 채팅방 검증 로직
    public boolean validationRoom(RoomKeyReq roomKeyReq){
        ChatRoom findChatRoom = chatRoomRepository.findById(roomKeyReq.getChatRoomId()).orElseThrow(() -> new NotFoundException("해당 채팅방은 없습니다"));

        if(roomKeyReq !=null && roomKeyReq.getRoomKey() == findChatRoom.getRoomKey()){
            return true;
        }
        return false;
    }


    public void roomSaveValidation(Long id , int week){
        log.info("같은 주차별 강의가 있는지 체크 로직");
        Boolean flag = chatRoomRepository.existsByCourseIdAndWeek(id, week); //jpa 내부에서 성능 최적화를 위해 모두 조회하지 않고 limit 1 으로 제한을 둠

        if(flag){
            throw new DuplicateCourseChatRoomException("해당 채팅방은 같은 주차로 이미 방이 생성 되었습니다");
        }
    }

    public ChatRoom getRoom(Long chatRoomId){
        log.info("채팅방 정보");

        return chatRoomRepository.findChatRoomById(chatRoomId).orElseThrow(()-> new NotFoundException("해당 채팅방은 없습니다"));
    }





}
