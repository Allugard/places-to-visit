package yo.antihype.team.service;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import yo.antihype.team.model.User;
import yo.antihype.team.repository.UserRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserServiceIntegrationTest {

    private UserRepository userRepository = new UserRepository();
    private UserService userService = new UserService(userRepository);

    @Test
    public void shouldFindAllUsers() {
        //GIVEN
        User user1 = new User(1, "Allugard", Date.from(Instant.now()));
        User user2 = new User(1, "Follkner", Date.from(Instant.now()));
        userService.createUser(user1);
        userService.createUser(user2);

        //WHEN
        List<User> result = userService.findAll();

        //THEN
        assertThat(result, IsCollectionContaining.hasItems(user1, user2));
        assertThat(result.size(), is(2));
    }

}
