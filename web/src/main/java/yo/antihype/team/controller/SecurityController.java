package yo.antihype.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import yo.antihype.team.model.User;
import yo.antihype.team.service.UserService;

@RestController
@RequestMapping("/")
public class SecurityController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PutMapping("/sign-up")
    public void createUser(@RequestBody User user)  {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.createUser(user);
    }

}


