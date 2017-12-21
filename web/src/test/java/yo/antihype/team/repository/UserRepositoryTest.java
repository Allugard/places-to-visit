package yo.antihype.team.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import yo.antihype.team.model.User;
import yo.antihype.team.repository.UserRepository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateUser() {
        //GIVEN
        User user = createUser();

        //WHEN
        user = userRepository.save(user);

        //THEN
        User res = entityManager.find(User.class, user.getId());
        assertThat(res.getEmail(), is(user.getEmail()));
        assertThat(res.getUsername(), is(user.getUsername()));
        assertThat(res.getPassword(), is(user.getPassword()));
    }

    @Test
    public void shouldFindUserById() {
        //GIVEN
        User user = createUser();
        entityManager.persist(user);
        entityManager.flush();

        //WHEN
        User result = userRepository.findOne(user.getId());

        //THEN
        assertThat(result.getEmail(), is(user.getEmail()));
        assertThat(result.getUsername(), is(user.getUsername()));
        assertThat(result.getPassword(), is(user.getPassword()));
    }

    @Test
    public void shouldDeleteUser() {
        //GIVEN
        User user = createUser();
        entityManager.persist(user);
        entityManager.flush();

        //WHEN
        userRepository.delete(user);

        //THEN
        User result = entityManager.find(User.class, user.getId());
        assertNull(result);
    }

    @Test
    public void shouldUpdateUser() {
        //GIVEN
        User user = createUser();
        entityManager.persist(user);
        entityManager.flush();

        user.setUsername("username");

        //WHEN
        userRepository.save(user);

        //THEN
        User result = entityManager.find(User.class, user.getId());
        assertThat(result.getEmail(), is(user.getEmail()));
        assertThat(result.getUsername(), is(user.getUsername()));
        assertThat(result.getPassword(), is(user.getPassword()));
    }

    private User createUser() {
        User user = new User();
        user.setEmail("example@email.com");
        user.setUsername("username");
        user.setPassword("pass");
        return user;
    }
}

