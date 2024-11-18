package team_project.clat.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.response.MemberAnswerResDTO;
import team_project.clat.dto.response.MemoResDTO;
import team_project.clat.dto.response.PageNationMessageResDTO;
import team_project.clat.repository.MemberRepository;

import java.util.List;

@SpringBootTest
@Transactional


class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MemoService memoService;

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
        List<MemoResDTO> byWithChatRoomMemo = memoService.findByWithChatRoomMemo(1L, 1L);

        //when

       //then
        for(MemoResDTO memoResDTO : byWithChatRoomMemo) {
            System.out.println(memoResDTO.getMemo());
        }
    }


    @Test
    @DisplayName("메시지 무한스크롤 구현")
    public void pageNationMessage() throws Exception {
       //given
        PageRequest pageRequest = PageRequest.of(1, 10);


       //when
        Slice<PageNationMessageResDTO> findPage = messageService.findNoOffSetByPageNationMessageList(1L,null ,pageRequest);

        //then

        for (PageNationMessageResDTO pageNationMessageResDTO : findPage) {

            System.out.println( pageNationMessageResDTO.getMessage());
        }


    }






}