package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT DISTINCT m FROM Message m JOIN FETCH m.images im WHERE m.chatRoom.id = :chatRoomId")
    List<Message> findByFileMessage(@Param("chatRoomId") Long chatRoomId);

}
