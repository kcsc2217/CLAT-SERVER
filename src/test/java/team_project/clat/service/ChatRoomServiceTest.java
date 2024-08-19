package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Message;
import team_project.clat.repository.ChatRoomRepository;


@SpringBootTest
@Transactional
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageService messageService;

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

    @Test
    public void ChatRoomFetchjoin쿼리최적화() throws Exception {
       //given

        Long saveId = chatRoomService.save("1-2", 1L);
        //then
        em.flush();
        em.clear();

       //when
        chatRoomRepository.findFetchChatRoomById(saveId).get();

       //then
    }

    @Test
    public void Fetchjoin을_사용한_메세지성능_최적화() throws Exception {

        //then

        ChatRoom chatRoom = chatRoomService.findFetchMessageCourseById(1L);

        for(Message  message: chatRoom.getMessageList()){
            System.out.println(message.getMessage());
        }

        //then
    }



}