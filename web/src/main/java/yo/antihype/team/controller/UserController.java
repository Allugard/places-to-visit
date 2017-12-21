package yo.antihype.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import yo.antihype.team.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/users/delete/all")
    public void deleteAll()  {
        userService.deleteAll();
    }

}
