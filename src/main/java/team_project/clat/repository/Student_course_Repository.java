package team_project.clat.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import team_project.clat.domain.Student_course;

import java.util.List;
import java.util.Optional;

public interface Student_course_Repository extends JpaRepository<Student_course, Long> {

    @Query("select st from Student_course st join fetch st.course c left join fetch c.chatRoom where st.member.id = :memberId") // 홈 화면에 회원의 강의목록 뿌리기
    List<Student_course> fetchStudentCourse(@Param("memberId") Long memberId);

    @Query("select st from Student_course st where st.member.id = :memberId and st.course.id = :courseId") // 회원이 강의를 듣는지 검증 로직
    Optional<Student_course> findByMemberIdAndCourseId(@Param("memberId") Long memberId, @Param("courseId") Long courseId);
}
