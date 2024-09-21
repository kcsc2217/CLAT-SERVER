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




}