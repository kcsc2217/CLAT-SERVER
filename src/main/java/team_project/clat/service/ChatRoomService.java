package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Course;
import team_project.clat.domain.Dto.request.ChatRoomCreateDto;
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

        ChatRoom chatRoom = new ChatRoom(roomName, week,  findCourse);

        ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);
        log.info("생성이 되었습니다");
        return saveChatRoom;
    }


    public ChatRoom findFetchMessageCourseById(Long courseId) {
        log.info("chatRoomId: {}", courseId);
        return chatRoomRepository.findFetchByMessage(courseId).orElseThrow(() ->  new NotFoundException("해당 채팅방은 없습니다"));
    }

    public ChatRoom findFetchMessageAndImage(Long chatRoomId){
        ChatRoom chatRoom = chatRoomRepository.findFetchByChatRoom(chatRoomId).orElseThrow(() -> new NotFoundException("해당 채팅방은 없습니다"));

        List<Long> messageIds = chatRoom.getMessageList().stream().map(Message::getId).collect(Collectors.toList());

        List<Message> allMessageIds = messageRepository.findAllMessageIds(messageIds);

        return  chatRoom;

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
        Boolean flag = chatRoomRepository.existsByCourseIdAndWeek(id, week);

        if(flag){
            throw new DuplicateCourseChatRoomException("해당 채팅방은 같은 주차로 이미 방이 생성 되었습니다");
        }
    }


    private void ValidationChatRoom(Long courseId) {
        boolean flag = chatRoomRepository.existsByCourseId(courseId);

        log.info("강의실 검증부준");

        if(flag == true){
            throw new DuplicateCourseChatRoomException("해당 강의의 채팅방은 이미 존재합니다");
        }
    }



}
