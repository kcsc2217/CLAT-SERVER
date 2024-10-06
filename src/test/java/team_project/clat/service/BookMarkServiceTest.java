package team_project.clat.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.dto.response.BookMarkSaveDTO;
import team_project.clat.dto.response.MessageBookMarkDTO;
import team_project.clat.repository.MemberRepository;

import java.util.List;

@SpringBootTest
@Transactional

class BookMarkServiceTest {

    @Autowired
    private BookMarkService bookMarkService;

    @Autowired
    private MemberRepository memberRepository;



    @Test
    public void 북마크_저장() throws Exception {
       //given
        Member member = memberRepository.findById(1L).get();
        BookMarkSaveDTO messageBookMarkResDTO = bookMarkService.markBook(14L, member);

        //when

       //then
        Assertions.assertThat(messageBookMarkResDTO.getMessageId()).isEqualTo(14L);
    }


    @Test
    public void 내가_등록한_북마크() throws Exception {
       //given

        Member member = memberRepository.findById(1L).get();

       //when
        List<MessageBookMarkDTO> all = bookMarkService.findAll(member);

        //then

        for (MessageBookMarkDTO messageBookMarkDTO : all) {
            System.out.println(messageBookMarkDTO.getMessageContent());
        }
    }

}