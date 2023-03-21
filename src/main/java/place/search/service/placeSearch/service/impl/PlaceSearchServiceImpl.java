package place.search.service.placeSearch.service.impl;

import place.search.service.placeSearch.client.ApiClient;
import place.search.service.placeSearch.dto.api.PlaceDto;
import place.search.service.placeSearch.util.StringUtil;
import place.search.service.placeSearch.dto.api.Place;
import place.search.service.placeSearch.dto.api.PlaceData;
import place.search.service.placeSearch.service.PlaceSearchServive;

import java.util.*;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceSearchServiceImpl implements PlaceSearchServive {
    private final List<ApiClient> placeApiClients;

    public List<PlaceDto> getPlaces(String query) {
        List<PlaceData> placeData =
                placeApiClients.stream()
                        .flatMap(placeApiClient -> placeApiClient.getPlaces(query).stream())
                        .map(Place::toPlaceData)
                        .collect(Collectors.toList());

        Queue<String> duplicatedQueue = new LinkedList<>();
        Queue<String> uniqueKaKaoQueue = new LinkedList<>();
        Queue<String> uniqueNaverQueue = new LinkedList<>();

        for (PlaceData place : placeData) {
            String placeName = StringUtil.eraseBoldTags(place.getName()).trim();
            String order = place.getOrder();

            if (uniqueKaKaoQueue.contains(placeName)
                    || uniqueNaverQueue.contains(placeName)) {
                if (uniqueKaKaoQueue.contains(placeName)) {
                    uniqueKaKaoQueue.remove(placeName);
                }

                if (uniqueNaverQueue.contains(placeName)) {
                    uniqueNaverQueue.remove(placeName);
                }

                duplicatedQueue.add(placeName);
            } else {
                if (!duplicatedQueue.contains(placeName)) {
                    if ("1".equals(order)) {
                        uniqueKaKaoQueue.add(placeName);
                    } else {
                        uniqueNaverQueue.add(placeName);
                    }
                }
            }
        }

        duplicatedQueue.addAll(uniqueKaKaoQueue);
        duplicatedQueue.addAll(uniqueNaverQueue);

        List<PlaceDto> rtnData = duplicatedQueue.stream()
                .map(it -> queueToPlaceDto(it))
                .collect(Collectors.toList());

        return rtnData;
    }

    private PlaceDto queueToPlaceDto(String placeName) {
        var dto = new PlaceDto();
        dto.setName(placeName);
        return dto;
    }
}
