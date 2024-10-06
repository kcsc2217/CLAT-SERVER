package team_project.clat.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.dto.response.MessageBookMarkResDTO;
import team_project.clat.repository.MemberRepository;

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
        MessageBookMarkResDTO messageBookMarkResDTO = bookMarkService.markBook(1L, member);

        //when

       //then
        Assertions.assertThat(messageBookMarkResDTO.getBookMarkId()).isEqualTo(1L);
    }

}