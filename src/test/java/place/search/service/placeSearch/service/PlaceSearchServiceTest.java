package place.search.service.placeSearch.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import place.search.service.placeSearch.client.ApiClient;
import place.search.service.placeSearch.client.KakaoApiClient;
import place.search.service.placeSearch.client.NaverApiClient;
import place.search.service.placeSearch.dto.api.KakaoPlaceDto;
import place.search.service.placeSearch.dto.api.NaverPlaceDto;
import place.search.service.placeSearch.dto.api.PlaceDto;
import place.search.service.placeSearch.service.impl.PlaceSearchServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PlaceSearchServiceTest {

    private PlaceSearchServive placeSearchService;
    private KakaoApiClient kakaoApiClient;
    private NaverApiClient naverApiClient;

    @BeforeEach
    public void init() {
        // given
        kakaoApiClient = mock(KakaoApiClient.class);
        naverApiClient = mock(NaverApiClient.class);
        List<ApiClient> apiClientList = new ArrayList<>();
        apiClientList.add(kakaoApiClient);
        apiClientList.add(naverApiClient);
        placeSearchService = new PlaceSearchServiceImpl(apiClientList);
    }

    @Test
    void testGetSortedPlaces() {
        KakaoPlaceDto kakaoPlaceDto1 = new KakaoPlaceDto();
        kakaoPlaceDto1.setPlaceName("치킨1");
        KakaoPlaceDto kakaoPlaceDto2 = new KakaoPlaceDto();
        kakaoPlaceDto2.setPlaceName("치킨2");
        KakaoPlaceDto kakaoPlaceDto3 = new KakaoPlaceDto();
        kakaoPlaceDto3.setPlaceName("치킨4");
        List<KakaoPlaceDto> kakaoPlaceDtoList = new ArrayList<>();
        kakaoPlaceDtoList.add(kakaoPlaceDto1);
        kakaoPlaceDtoList.add(kakaoPlaceDto2);
        kakaoPlaceDtoList.add(kakaoPlaceDto3);
        given(kakaoApiClient.getPlaces(anyString())).willReturn(kakaoPlaceDtoList);

        NaverPlaceDto naverPlaceDto1 = new NaverPlaceDto();
        naverPlaceDto1.setTitle("치킨1");
        NaverPlaceDto naverPlaceDto2 = new NaverPlaceDto();
        naverPlaceDto2.setTitle("치킨2");
        NaverPlaceDto naverPlaceDto3 = new NaverPlaceDto();
        naverPlaceDto3.setTitle("치킨3");
        List<NaverPlaceDto> naverPlaceDtoList = new ArrayList<>();
        naverPlaceDtoList.add(naverPlaceDto1);
        naverPlaceDtoList.add(naverPlaceDto2);
        naverPlaceDtoList.add(naverPlaceDto3);
        given(naverApiClient.getPlaces(anyString())).willReturn(naverPlaceDtoList);

        // when
        List<PlaceDto> places = placeSearchService.getPlaces("query");

        // then
        assertEquals(places.get(0).getName(), "치킨1");
        assertEquals(places.get(1).getName(), "치킨2");
        assertEquals(places.get(2).getName(), "치킨4");
        assertEquals(places.get(3).getName(), "치킨3");
    }
}
