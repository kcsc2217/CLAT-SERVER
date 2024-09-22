package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select distinct cr from ChatRoom cr left join fetch cr.messageList me where cr.course.id = :courseId")
    Optional<ChatRoom> findFetchByMessage(@Param("courseId") Long courseId);

    @Query("select distinct cr from ChatRoom cr left join fetch cr.messageList m join fetch cr.course c where cr.id = :chatRoomId")
    Optional<ChatRoom> findFetchByCourseAndMessage(@Param("chatRoomId") Long chatRoomId);

    @Query("select distinct cr from ChatRoom cr left join fetch cr.messageList me where cr.id = :chatRoomId")
    Optional<ChatRoom> findFetchByChatRoom(@Param("chatRoomId") Long chatRoomId);

    boolean existsByCourseIdAndWeek(Long courseId, int week);  // ChatRoom 의 외래키인 course_id를 가르킴

    @Query("select cr from ChatRoom cr join fetch cr.course cor where cr.id = :chatRoomId")
    Optional<ChatRoom> findChatRoomById (@Param("chatRoomId") Long chatRoomId);







}
