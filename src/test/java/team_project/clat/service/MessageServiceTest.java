package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.domain.Memo;
import team_project.clat.domain.Message;
import team_project.clat.dto.FileImageDTO;
import team_project.clat.repository.MemberRepository;
import team_project.clat.repository.MemoRepository;

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
    private MemoRepository memoRepository;

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
    public void 멤버의_메시지에달린_답글() throws Exception {
       //given
        List<Message> test = messageService.findByWithAnswer(1L);

        //when

       //then
       for(Message m : test) {
           System.out.println(m.getAnswer().getAnswer());
       }
    }



}