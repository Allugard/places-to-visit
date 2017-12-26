package yo.antihype.team.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import yo.antihype.team.model.Place;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Long> {

    @Query(value = "SELECT * FROM place JOIN user_place ON place.id = user_place.place_id JOIN users ON user_place.user_id = users.id" +
            "  WHERE users.username  = :username", nativeQuery = true)
    List<Place> findPlacesByUsername(@Param("username") String username);
    Place findByLatAndLng(String lat, String lng);

}
