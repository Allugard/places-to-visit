package yo.antihype.team.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yo.antihype.team.model.User;

import java.util.Date;

/**
 * Created by Serhii_Vasylenko on 9/19/2017.
 */
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @RequestMapping("/some")
    @ResponseBody
    public User home() {
        return new User(1, "Ser", new Date());
    }


}
