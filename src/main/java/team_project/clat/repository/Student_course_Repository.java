package team_project.clat.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Student_course;

import java.util.List;

public interface Student_course_Repository extends JpaRepository<Student_course, Long> {

    @Query("select st from Student_course st join fetch st.course c where st.member.id = :memberId")
    List<Student_course> fetchStudentCourse(@Param("memberId") Long memberId);
}
