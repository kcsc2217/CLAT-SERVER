package team_project.clat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team_project.clat.domain.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
}
