package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.BookMark;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    boolean existsByMemberIdAndMessageId(Long memberId, Long messageId);
}
