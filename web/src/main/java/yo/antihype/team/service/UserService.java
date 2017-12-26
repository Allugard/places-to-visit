package yo.antihype.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yo.antihype.team.exception.DuplicateFieldException;
import yo.antihype.team.model.Place;
import yo.antihype.team.model.User;
import yo.antihype.team.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PlaceService placeService;

    @Autowired
    public UserService(UserRepository userRepository, PlaceService placeService) {
        this.userRepository = userRepository;
        this.placeService = placeService;
    }

    public User createUser(User user) {
        User save = null;
        try {
            save = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateFieldException(e.getCause().getCause().getMessage());
        }
        return save;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Transactional
    public Place addPlace(String username, Place place) {
        Place currrent = placeService.createPlace(place);

        User user = userRepository.findByUsername(username);
        if (!user.getPlaces().contains(currrent)) {
            user.getPlaces().add(currrent);
        }

        userRepository.save(user);
        return currrent;
    }

    @Transactional
    public void deletePlace(String username, Place place) {
        User user = userRepository.findByUsername(username);
        user.getPlaces().remove(place);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.emptyList());
    }

}
