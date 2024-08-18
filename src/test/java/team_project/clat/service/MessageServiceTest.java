package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Message;
import team_project.clat.repository.MemberRepository;
import team_project.clat.repository.MessageRepository;

@SpringBootTest
@Transactional

class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private EntityManager em;

    @Test
    public void 메세지생성() throws Exception {
       //given
        Long chatRoomId = chatRoomService.save("1-2", 1L);

        em.flush();
        em.clear();

        Long findId = messageService.saveMessage("이성원", chatRoomId, "Hello World");
        em.flush();
        em.clear();

        Message message = messageRepository.findById(findId).get();
        //then
        Assertions.assertThat(message.getMessage()).isEqualTo("Hello World");
        Assertions.assertThat(message.getChatRoom().getRoomName()).isEqualTo("1-2");


    }

}