package place.search.service.placeSearch.client;

import org.springframework.web.client.RestTemplate;
import place.search.service.placeSearch.dto.api.KakaoPlaceDto;
import place.search.service.placeSearch.dto.api.Place;
import place.search.service.placeSearch.dto.api.SearchListResponse;
import place.search.service.placeSearch.exception.ClientException;
import place.search.service.placeSearch.properties.KakaoApiProperties;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "openapi.kakao", name = "base-url")
@Component
public class KakaoApiClient implements ApiClient {
    private static final String AUTH_HEADER_PREFIX = "KakaoAK ";

    private final KakaoApiProperties properties;

    @Override
    public List<KakaoPlaceDto> getPlaces(String query) {
        String uri =
                properties.getBaseUrl()
                        + UriComponentsBuilder.fromUriString(properties.getApi().get("places"))
                        .buildAndExpand(query);

        SearchListResponse<KakaoPlaceDto> response;

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, AUTH_HEADER_PREFIX + properties.getApiKey());

        try {
            response = new RestTemplate().exchange(
                                    uri,
                                    HttpMethod.GET,
                                    new HttpEntity<>(headers),
                                    new ParameterizedTypeReference<SearchListResponse<KakaoPlaceDto>>() {
                                    })
                            .getBody();
        } catch (Exception ex) {
            throw new ClientException(ex);
        }

        if (response != null) {
            return response.get();
        } else {
            log.warn("> external api(Kakao) returns empty response.");
            return Collections.emptyList();
        }
    }

}
