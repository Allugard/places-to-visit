package yo.antihype.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import yo.antihype.team.dto.EmptyDto;
import yo.antihype.team.model.User;
import yo.antihype.team.service.UserService;

@RestController
public class SecurityController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/sign-up", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<EmptyDto> createUser(@RequestBody User user)  {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return new ResponseEntity<>(new EmptyDto(), HttpStatus.OK);
    }

}


