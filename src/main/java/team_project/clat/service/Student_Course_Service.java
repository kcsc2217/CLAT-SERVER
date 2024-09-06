package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Course;
import team_project.clat.domain.Student_course;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.CourseRepository;
import team_project.clat.repository.Student_course_Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Student_Course_Service {

    private final Student_course_Repository studentCourseRepository;
    private final CourseRepository courseRepository;


    public List<Course> courseList(Long memberId, String term){
        List<Student_course> studentCourses = studentCourseRepository.fetchStudentCourseWithoutChatRooms(memberId);

        if(studentCourses.isEmpty()){
            throw new NotFoundException("해당 멤버는 듣는 강의가 없습니다");
        }

        List<Long> courseIds = studentCourses.stream()
                .map(sc -> sc.getCourse().getId())
                .collect(Collectors.toList());


        List<Course> coursesWithChatRooms = courseRepository.fetchCoursesWithChatRooms(courseIds, term);

        return coursesWithChatRooms;
    }




}
