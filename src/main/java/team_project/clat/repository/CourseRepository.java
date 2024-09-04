package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.chatRoomList WHERE c.id IN :courseIds AND  c.term =:term")
    List<Course> fetchCoursesWithChatRooms(@Param("courseIds") List<Long> courseIds, @Param("term") String term);








//    @Query("select distinct c from Course c join fetch ")


}
