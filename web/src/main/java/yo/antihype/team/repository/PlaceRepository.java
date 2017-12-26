package yo.antihype.team.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import yo.antihype.team.model.Place;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Long> {

    @Query(value = "SELECT * FROM PLACE JOIN USERS_PLACE ON PLACE.ID = USERS_PLACE.PLACE_ID JOIN USERS ON USERS_PLACE.USERS_ID = USERS.ID" +
            "  WHERE USERS.USERNAME = :username", nativeQuery = true)
    List<Place> findPlacesByUsername(@Param("username") String username);
    Place findByLatAndLng(String lat, String lng);

}
