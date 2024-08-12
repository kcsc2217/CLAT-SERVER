package team_project.clat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
