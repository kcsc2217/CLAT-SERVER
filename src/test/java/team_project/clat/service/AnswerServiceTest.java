package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Answer;

@SpringBootTest
@Transactional

class AnswerServiceTest {
    @Autowired
    private AnswerService answerService;


    @Autowired
    private EntityManager em;



    @Test
    public void 답변_생성() throws Exception {
       //given
        Answer findByAnswer =  answerService.saveAnswer("기특하구나", "pro1", 9L);

        //when

        em.flush();
        em.clear();

//        Answer findByAnswer = answerRepository.getAnswer(id);



        //then
        Assertions.assertThat(findByAnswer.getAnswer()).isEqualTo("기특하구나");
        Assertions.assertThat(findByAnswer.getMessage().getId()).isEqualTo(9L);
        Assertions.assertThat(findByAnswer.getMember().getUsername()).isEqualTo("pro1");
    }

}