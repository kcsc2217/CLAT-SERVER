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


    public List<Course> courseList(Long memberId, String term){  // course와 chatRoom fetch join 시킴   //쿼리 성능 최적화
        List<Student_course> studentCourses = studentCourseRepository.fetchStudentCourseWithoutChatRooms(memberId); // 멤버 아이디에 맞는 수강 가지고 옴 대신 course를 fetch join

        if(studentCourses.isEmpty()){
            throw new NotFoundException("해당 멤버는 듣는 강의가 없습니다");
        }

        List<Long> courseIds = studentCourses.stream()  //그에 준하는 courseId를 List로 가져옴 -> 즉 해당 멤버의 강의들의 아이디를 가져옴
                .map(sc -> sc.getCourse().getId())
                .collect(Collectors.toList());


        List<Course> coursesWithChatRooms = courseRepository.fetchCoursesWithChatRooms(courseIds, term);  //해당 course를 chatroom을 fetch join  (멤버id에 courseId와 학기 term 을 같이 조회) 맞는 course들을 가져옴

        return coursesWithChatRooms;
    }




}
