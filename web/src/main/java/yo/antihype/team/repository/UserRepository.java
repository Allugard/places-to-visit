package yo.antihype.team.repository;

import org.springframework.data.repository.CrudRepository;
import yo.antihype.team.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
