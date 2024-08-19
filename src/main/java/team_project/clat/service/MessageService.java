package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Course;
import team_project.clat.domain.Message;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.ChatRoomRepository;
import team_project.clat.repository.CourseRepository;
import team_project.clat.repository.MessageRepository;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final CourseRepository courseRepository;


    @Transactional
    public Long save(Message message) {
        return messageRepository.save(message).getId();
    }


    @Transactional
    public Message saveMessage(String senderName, Long courseId, String message){ //controller 에서 해당 회원이 메세지를 사용할 수 있는지 검증
//        ChatRoom findByChatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다"));
        Course findCourse = courseRepository.findFetchCourseById(courseId).orElseThrow(() -> new IllegalArgumentException("해당 강의는 없습니다"));
        log.info("찾는로그");
        ChatRoom chatRoom = findCourse.getChatRoom();
        Message saveMessage = Message.createMessage(senderName, chatRoom, message);
        log.info("메세지 생성완료");

        Long saveId = save(saveMessage);

        return messageRepository.findById(saveId).orElseThrow(()-> new NotFoundException("해당 메세지는 없습니다"));
    }

    public Message findById(Long id){
        return messageRepository.findById(id).orElseThrow(()-> new NotFoundException("해당 메세지는 없습니다"));
    }



}
