package yo.antihype.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
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



    @Retryable(
            value = CannotCreateTransactionException.class,
            backoff = @Backoff(delay = 5000,
                    maxDelay = 100000,
                    multiplier = 2))
    public Place createPlace(Place place) {
        Place cur = placeRepository.findByLatAndLng(place.getLat(), place.getLng());
        if (cur == null) {
            cur = placeRepository.save(place);
        }
        return cur;
    }

    @Retryable(
            value = CannotCreateTransactionException.class,
            backoff = @Backoff(delay = 5000,
                    maxDelay = 100000,
                    multiplier = 2))
    public List<Place> findPlacesByUsername(String username) {
        return placeRepository.findPlacesByUsername(username);
    }

}
