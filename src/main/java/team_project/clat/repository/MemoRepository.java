package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
