package yo.antihype.team.service;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import yo.antihype.team.model.User;
import yo.antihype.team.repository.UserRepository;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserRepository userRepository = mock(UserRepository.class);

    private UserService sut = new UserService(userRepository);

    @Test
    public void shouldCreateUser() {
        //GIVEN
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        //WHEN
        User result = sut.createUser(new User());

        //THEN
        assertThat(result, CoreMatchers.is(user));
    }

}
