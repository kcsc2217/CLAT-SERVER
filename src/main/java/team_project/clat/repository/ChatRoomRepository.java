package team_project.clat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.ChatRoom;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    boolean existsByCourseId(Long courseId);

    @Query("select cr from ChatRoom cr join fetch cr.course c where cr.id = :chatRoomId")
    Optional<ChatRoom> findFetchChatRoomById(@Param("chatRoomId") Long chatRoomId);

    @Query("select distinct cr from ChatRoom cr left join fetch cr.messageList me where cr.id = :chatRoomId")
    Optional<ChatRoom> findFetchByMessage(@Param("chatRoomId") Long chatRoomId);



}
