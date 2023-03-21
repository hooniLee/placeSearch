package place.search.service.placeSearch.service;

import place.search.service.placeSearch.entity.PlaceSearchCount;

import java.util.List;

public interface PlaceSearchCountService {
    void addCount(String query);

    List<PlaceSearchCount> getTopSearchedCounts();
}
