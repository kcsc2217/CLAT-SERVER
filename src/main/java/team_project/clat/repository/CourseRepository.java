package team_project.clat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {


}
