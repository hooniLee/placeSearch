package place.search.service.placeSearch.client;

import place.search.service.placeSearch.dto.api.Place;

import java.util.List;

public interface ApiClient {
  List<? extends Place> getPlaces(String query);
}
