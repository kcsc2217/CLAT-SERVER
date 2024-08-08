package team_project.clat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
