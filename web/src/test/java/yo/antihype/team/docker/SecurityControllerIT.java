package yo.antihype.team.docker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import yo.antihype.team.model.User;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
public class SecurityControllerIT {

    private static final String DOCKER_URL = "http://192.168.99.100:9001";

    private TestRestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new TestRestTemplate();
        cleanUsers();
    }

    @After
    public void clean() {
        cleanUsers();
    }

    @Test
    public void whenSendRequestWithRightValuesThenReturnOkStatus() {
        User user = createUser();
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<String> response = restTemplate.exchange(DOCKER_URL + "/sign-up", HttpMethod.PUT, request, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void whenSendRequestWithoutJwtToSecuredResourceThenReturnForbiddenStatus() {
        ResponseEntity<String> response = restTemplate.getForEntity(DOCKER_URL + "/app/info", String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void whenSendRequestWithNotExpiredJwtToSecuredResourceAppInfoThenReturnOkStatusAndGitVersion() {
        User user = createUser();
        HttpEntity<User> request = new HttpEntity<>(user);
        restTemplate.exchange(DOCKER_URL + "/sign-up", HttpMethod.PUT, request, String.class);

        user.setEmail(null);
        request = new HttpEntity<>(user);
        ResponseEntity<String> loginResponse = restTemplate.exchange(DOCKER_URL + "/login", HttpMethod.POST, request, String.class);
        String jwt = loginResponse.getHeaders().getFirst("Authorization");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", jwt);
        HttpEntity<Void> requestWithJwt = new HttpEntity<Void>(headers);
        ResponseEntity<String> response = restTemplate.exchange(DOCKER_URL + "/app/info", HttpMethod.GET, requestWithJwt, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    private User createUser() {
        User user = new User();
        user.setPassword("pass");
        user.setUsername("username");
        user.setEmail("email");
        return user;
    }

    private void cleanUsers() {
        restTemplate.delete(DOCKER_URL + "/users/delete/all");
    }

}
