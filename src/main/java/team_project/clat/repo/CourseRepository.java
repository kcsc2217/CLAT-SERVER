package team_project.clat.repo;

import org.springframework.data.repository.CrudRepository;
import team_project.clat.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
