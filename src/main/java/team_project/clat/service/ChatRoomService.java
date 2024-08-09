package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Course;
import team_project.clat.domain.Message;
import team_project.clat.repo.ChatRoomRepository;
import team_project.clat.repo.CourseRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final CourseRepository courseRepository;



    @Transactional
    public Long save(String roomName, Long courseId) {

        Course findCourse = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found")); //강의실 아이디찾기

        log.info("수업 찾기 완료");

        ChatRoom chatRoom = new ChatRoom(roomName, findCourse);

        ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);
        log.info("생성이 되었습니다");
        return saveChatRoom.getId();
    }


    @Transactional
    public void setMessage(Long chatRoomId, Message message) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다"));

        log.info("charRoom 찾기 완료 ");

        message.addChatRoom(chatRoom);

    }



}
