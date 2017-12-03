package yo.antihype.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yo.antihype.team.model.User;
import yo.antihype.team.service.UserService;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/users")
    @ResponseBody
    public void createUser(@RequestBody User user)  {
        userService.createUser(user);
    }

}


