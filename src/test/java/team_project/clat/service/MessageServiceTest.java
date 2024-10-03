package team_project.clat.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.request.MessageMemoReqDTO;
import team_project.clat.dto.response.MemberAnswerResDTO;
import team_project.clat.dto.response.MemoResDTO;
import team_project.clat.exception.UnAuthorizationException;
import team_project.clat.repository.MemberRepository;

import java.util.List;

@SpringBootTest
@Transactional


class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MemberRepository memberRepository;



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
        List<MemberAnswerResDTO> byWithAnswer = messageService.findByWithAnswer(1L);

        //when

       //then
      for(MemberAnswerResDTO memberAnswerResDTO : byWithAnswer) {
          System.out.println(memberAnswerResDTO);
      }
    }


    @Test
    public void 채팅방의_멤버의_메모쿼리보기() throws Exception {
       //given
        List<MemoResDTO> byWithChatRoomMemo = messageService.findByWithChatRoomMemo(1L, 1L);

        //when

       //then
        for(MemoResDTO memoResDTO : byWithChatRoomMemo) {
            System.out.println(memoResDTO.getMemo());
        }
    }

    @Test
    public void 메모_검증() throws Exception {

       //then
        Assertions.assertThrows(UnAuthorizationException.class, () -> {
            messageService.saveMemo(new MessageMemoReqDTO(7L, "안녕"),1L);
        });
    }



}