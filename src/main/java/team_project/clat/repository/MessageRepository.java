package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select distinct  m from Message m  left join fetch m. member left join fetch m.images where m.id IN:messageIds")
    List<Message> findAllMessageIds(@Param("messageIds") List<Long> messageIds);

    @Query("select m from Message m  left join fetch m.memo me where m.id = :messageId")
    Optional<Message> findMessageById(@Param("messageId") Long messageId);



}
