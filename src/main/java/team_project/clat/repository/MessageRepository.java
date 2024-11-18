package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Message;
import team_project.clat.repository.querydsl.MessageRepositoryCustom;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> , MessageRepositoryCustom {


    @Query("select m from Message m  join fetch m.memo me where m.id = :messageId")
    Optional<Message> findMessageById(@Param("messageId") Long messageId);


    @Query("select m from Message m join fetch m.member mem where m.id = :messageId")
    Optional<Message> findMessageByMemberId(@Param("messageId") Long messageId);


    @Query("select m from Message m join fetch m.answer an where m.member.id = :memberId")
    Optional<List<Message>> findMessageByUsername(@Param("memberId") Long memberId);

    @Query("select m from Message m  join fetch m.memo where m.chatRoom.id = :chatRoomId and m.member.id = :memberId")
    Optional<List<Message>> findByChatRoomId(@Param("chatRoomId") Long chatRoomId, @Param("memberId") Long memberId);

    @Query("select m from Message m  join fetch m.memo  join fetch m.member where m.id = :messageId")
    Optional<Message> findFetchMemoByMessageId(@Param("messageId") Long messageId);

    @Query("select  distinct m from Message m join fetch m.member left join fetch m.images left join fetch m.answer an where m.chatRoom.id = :chatRoomId")
    Optional<List<Message>> findQueryUpdateByChatRoomId(Long chatRoomId);

    @Query("select distinct m from Message m left join fetch m.likes where m.id IN:messageIds")
    List<Message> findLikeByAllMessagesByIds(@Param("messageIds") List<Long> messageIds);

}
