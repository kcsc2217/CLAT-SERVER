package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.BookMark;

import java.util.List;
import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    boolean existsByMemberIdAndMessageId(Long memberId, Long messageId);

    @Query("select bm from BookMark bm join fetch bm.message where bm.member.id = :memberId")
    Optional<List<BookMark>> findByMemberId(@Param("memberId") Long memberId);
}
