package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c join fetch c.chatRoom cr where c.id = :courseId")
    Optional<Course> findFetchCourseById(@Param("courseId") Long courseId);


//    @Query("select distinct c from Course c join fetch ")


}
