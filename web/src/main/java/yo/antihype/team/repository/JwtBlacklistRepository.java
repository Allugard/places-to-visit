package yo.antihype.team.repository;

import org.springframework.data.repository.CrudRepository;
import yo.antihype.team.model.JwtBlacklist;

public interface JwtBlacklistRepository extends CrudRepository<JwtBlacklist, Long> {
    JwtBlacklist findByToken(String token);
}
