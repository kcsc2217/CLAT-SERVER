package team_project.clat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
