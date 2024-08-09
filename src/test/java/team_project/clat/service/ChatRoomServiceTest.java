package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Course;
import team_project.clat.domain.TimeTable;
import team_project.clat.repo.ChatRoomRepository;
import team_project.clat.repo.CourseRepository;


@SpringBootTest
@Transactional
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private EntityManager em;


    @Test
    public void 연관관계() throws Exception{

        em.flush();
        em.clear();

        Long saveId = chatRoomService.save("1-2", 1L);
        //then
        ChatRoom findChatRoom = chatRoomRepository.findById(saveId).get();


        Assertions.assertThat(findChatRoom.getRoomName()).isEqualTo("1-2");
        Assertions.assertThat(findChatRoom.getCourse().getRoom()).isEqualTo("공5102");

    }



}