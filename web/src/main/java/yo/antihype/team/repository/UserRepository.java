package yo.antihype.team.repository;

import yo.antihype.team.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public boolean createUser(User user) {
        return users.add(user);
    }

    public List<User> findAll() {
        return users;
    }
}
