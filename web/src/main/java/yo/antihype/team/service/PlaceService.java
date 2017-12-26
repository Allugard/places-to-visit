package yo.antihype.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yo.antihype.team.model.Place;
import yo.antihype.team.repository.PlaceRepository;

import java.util.List;

@Service
public class PlaceService  {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> findPlacesByUsername(String username) {
        return placeRepository.findPlacesByUsername(username);
    }

    public void delete(Place place) {
        placeRepository.delete(place);
    }
}
