package team_project.clat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Course;
import team_project.clat.repository.CourseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class Student_Course_ServiceTest {

    @Autowired
    private Student_Course_Service studentCourseService;

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void fetch_join_student_course_Test() throws Exception {
       //given
     studentCourseService.courseList(1L);

        //when


       //then
    }

}