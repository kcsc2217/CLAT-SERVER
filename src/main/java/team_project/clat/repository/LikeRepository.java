package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select l from Like l where l.message.id = :messageId")
    List<Like> findByMessageId(@Param("messageId") Long messageId);

    boolean existsByMessageIdAndMemberId(Long messageId, Long memberId);

}
