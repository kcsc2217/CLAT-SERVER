package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.File.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
