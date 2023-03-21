package place.search.service.placeSearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import place.search.service.placeSearch.entity.PlaceSearchCount;

@Repository
public interface  PlaceSearchCountRepository extends JpaRepository<PlaceSearchCount,String> {
}
