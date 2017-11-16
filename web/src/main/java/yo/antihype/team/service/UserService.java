package yo.antihype.team.service;

import yo.antihype.team.model.User;
import yo.antihype.team.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(User user) {
        return userRepository.createUser(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
