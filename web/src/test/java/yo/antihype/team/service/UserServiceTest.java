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
        when(userRepository.createUser(any(User.class))).thenReturn(true);

        //WHEN
        boolean result = sut.createUser(new User());

        //THEN
        assertThat(result, CoreMatchers.is(true));
    }

}
