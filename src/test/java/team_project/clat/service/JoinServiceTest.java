package team_project.clat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.dto.request.JoinReqDTO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JoinServiceTest {

    @Autowired
    JoinService joinService;

    @Test
    void joinProcess() throws IOException {
        JoinReqDTO joinReqDTO = new JoinReqDTO("홍길동", "gilldong","asdasd123!!","길동대학교", UserType.STUDENT);
        JoinReqDTO joinReqDTO1 = new JoinReqDTO("고길동", "gilldong","asdasd123!!","길동대학교", UserType.STUDENT);
        joinService.joinProcess(joinReqDTO,null);
        joinService.joinProcess(joinReqDTO1,null);
    }
}