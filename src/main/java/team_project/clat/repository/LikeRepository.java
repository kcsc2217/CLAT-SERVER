package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.Like;
import team_project.clat.repository.querydsl.LikeRepositoryCustom;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long>, LikeRepositoryCustom {

    @Query("select l.emoticon, COUNT(l) from Like l where l.message.id = :messageId GROUP BY l.emoticon")
    List<Object[]> findByMessageId(@Param("messageId") Long messageId);

    boolean existsByMessageIdAndMemberId(Long messageId, Long memberId);

}
