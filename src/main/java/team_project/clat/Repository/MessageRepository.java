package team_project.clat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
