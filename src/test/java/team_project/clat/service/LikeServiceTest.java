package team_project.clat.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Enum.Emoticon;
import team_project.clat.domain.Like;
import team_project.clat.repository.LikeRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class LikeServiceTest {

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private EntityManager em;


    @Test
    public void 메세지_좋아요매핑() throws Exception {
       //given
        Long likeId = likeService.like(1L, Emoticon.LIKE);

        //when

        em.flush();
        em.clear();

        Like like = likeRepository.findById(likeId).get();

        //then

        Assertions.assertThat(like.getEmoticon().getDescription()).isEqualTo("좋아요");
    }

}