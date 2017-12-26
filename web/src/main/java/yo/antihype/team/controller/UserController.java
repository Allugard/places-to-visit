package yo.antihype.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yo.antihype.team.dto.EmptyDto;
import yo.antihype.team.model.Place;
import yo.antihype.team.service.PlaceService;
import yo.antihype.team.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final PlaceService placeService;

    @Autowired
    public UserController(UserService userService, PlaceService placeService) {
        this.userService = userService;
        this.placeService = placeService;
    }

    @PutMapping("user/place")
    public Place addPlace(@RequestBody Place place, @CookieValue("username") String username) {
        return userService.addPlace(username, place);
    }

    @DeleteMapping("user/place")
    public ResponseEntity<EmptyDto> deletePlace(@RequestBody Place place, @CookieValue("username") String username) {
        userService.deletePlace(username, place);
        return new ResponseEntity<>(new EmptyDto(), HttpStatus.OK);
    }

    @GetMapping("user/places")
    public List<Place> getPlaces(@CookieValue("username") String username) {
        return placeService.findPlacesByUsername(username);
    }

    @DeleteMapping("/users/delete/all")
    public ResponseEntity<EmptyDto> deleteAll()  {
        userService.deleteAll();
        return new ResponseEntity<>(new EmptyDto(), HttpStatus.OK);
    }

}
