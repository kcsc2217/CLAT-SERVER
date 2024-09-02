package team_project.clat.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Course;
import team_project.clat.repository.CourseRepository;
import team_project.clat.repository.Student_course_Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class Student_Course_ServiceTest {

    @Autowired
    private Student_Course_Service studentCourseService;

    @Autowired
    private Student_course_Repository studentCourseRepository;



    @Test
    public void fetch_join_student_course_Test() throws Exception {
       //given
     studentCourseRepository.fetchStudentCourse(1L);

        //when
       //then
    }

//    @Test
//    public void 쿼리_보기() throws Exception {
//       //given
//        boolean admin = courseService.existUseMemberCourse("admin", 46L);
//
//        //when
//
//        Assertions.assertThat(admin).isEqualTo(false);
//
//       //then
//    }

}