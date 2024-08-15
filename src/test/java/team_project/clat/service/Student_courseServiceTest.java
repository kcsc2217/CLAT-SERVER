package team_project.clat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class Student_courseServiceTest {
    @Autowired
    private Student_courseService student_courseService;


    @Test
    @Rollback(false)
    public void 강의_더미데이터_넣기() throws Exception {
       //given
        student_courseService.createStudent_course(1L, 3L);
        student_courseService.createStudent_course(1L, 4L);
        student_courseService.createStudent_course(1L, 5L);
        student_courseService.createStudent_course(1L, 6L);
        student_courseService.createStudent_course(1L, 8L);
        student_courseService.createStudent_course(1L, 27L);
        student_courseService.createStudent_course(1L, 31L);
        student_courseService.createStudent_course(1L, 40L);

        //when

       //then
    }

}