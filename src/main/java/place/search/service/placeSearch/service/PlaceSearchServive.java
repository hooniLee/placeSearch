package place.search.service.placeSearch.service;

import place.search.service.placeSearch.dto.api.PlaceDto;

import java.util.List;

public interface PlaceSearchServive {
    public List<PlaceDto> getPlaces(String query);
}
