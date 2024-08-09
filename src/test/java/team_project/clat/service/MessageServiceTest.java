package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Address;
import team_project.clat.domain.Enum.Gender;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.repo.MemberRepository;
import team_project.clat.repo.MessageRepository;

import static org.junit.jupiter.api.Assertions.*;

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
        Member testMember = new Member(
                "john_doe",               // nickName
                "john.doe@example.com",   // email
                "securePassword123",      // password
                30,                       // age
                Gender.MALE,              // gender (enum 값 예시)
                UserType.STUDENT,         // userType (enum 값 예시)
                new Address("123 Main St", "New York", "NY")// address
        );

        //when

        memberRepository.save(testMember);
        Long chatRoomId = chatRoomService.save("1-2", 1L);

        em.flush();
        em.clear();

        Long findId = messageService.saveMessage(testMember.getId(), chatRoomId, "Hello World");
        em.flush();
        em.clear();

        Message message = messageRepository.findById(findId).get();
        //then
        Assertions.assertThat(message.getMessage()).isEqualTo("Hello World");
        Assertions.assertThat(message.getMember().getNickName()).isEqualTo("john_doe");

    }

}