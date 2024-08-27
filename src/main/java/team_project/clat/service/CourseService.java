package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team_project.clat.domain.Member;
import team_project.clat.domain.Student_course;
import team_project.clat.repository.CourseRepository;
import team_project.clat.repository.MemberRepository;
import team_project.clat.repository.Student_course_Repository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor

public class CourseService {
    private final MemberRepository memberRepository;

    private final Student_course_Repository studentCourseRepository;

    public boolean existUseMemberCourse(Member member, Long courseId){

        Optional<Student_course> findByStudent_course = studentCourseRepository.findByMemberIdAndCourseId(member.getId(), courseId);

        if(findByStudent_course.isPresent()){
            return true;
        }
        return false;


    }



}
