package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.FileImageDTO;
import team_project.clat.repository.MemberRepository;
import team_project.clat.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional


class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 메세지생성() throws Exception {
       //given
        Member member = memberRepository.findById(1L).get();


        Message message = messageService.saveMessage(member.getUsername(), 1L, "Hello World");


        //then
        Assertions.assertThat(message.getMember().getUsername()).isEqualTo("admin");


    }

    @Test
    public void 파일_메세지_생성쿼리() throws Exception {

       //given
        Member member = memberRepository.findById(1L).get();

        List<FileImageDTO> fileImageDTOList = new ArrayList<>();

        fileImageDTOList.add(new FileImageDTO(3L, "https://sung-won-chat.s3.ap-northeast-2.amazonaws.com/chat-service/efdeea52-51ff-48d5-b1d6-420002d33d5c.jpg"));
        fileImageDTOList.add(new FileImageDTO(4L, "https://sung-won-chat.s3.ap-northeast-2.amazonaws.com/chat-service/efdeea52-51ff-48d5-b1d6-420002d33d5c.jpg"));


        Message message = messageService.saveFileMessage(member.getUsername(), 1L, fileImageDTOList);


        //when

        Assertions.assertThat(message.getMember().getUsername()).isEqualTo("admin");
        Assertions.assertThat(message.getImages().size()).isEqualTo(2);

       //then
    }

    @Test
    public void 메세지_생성쿼리_확인() throws Exception {
       //given
        Member member = memberRepository.findById(1L).get();

        List<Message> messages = member.getMessageList();

        System.out.println(messages.size());




        //then
    }

    @Test
    @Rollback(false)
    public void 메모_쿼리보기() throws Exception {
       //given
        messageService.saveMemo(1L, "Hello World");
       //when

       //then
    }

    @Test
    public void 메모_쿼리_보기() throws Exception {
       //given
        Message message = messageService.findByWithMemo(3L);
        //when

       //then
        System.out.println("message.getMemo().getMemo() = " + message.getMemo().getMemo());
    }

}