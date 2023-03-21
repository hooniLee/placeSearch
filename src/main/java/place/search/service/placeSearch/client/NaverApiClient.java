package place.search.service.placeSearch.client;

import place.search.service.placeSearch.dto.api.KakaoPlaceDto;
import place.search.service.placeSearch.dto.api.NaverPlaceDto;
import place.search.service.placeSearch.dto.api.Place;
import place.search.service.placeSearch.dto.api.SearchListResponse;
import place.search.service.placeSearch.exception.ClientException;
import place.search.service.placeSearch.properties.NaverApiProperties;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "openapi.naver", name = "base-url")
@Component
public class NaverApiClient implements ApiClient {
  private static final String AUTH_HEADER_KEY_ID = "X-Naver-Client-Id";
  private static final String AUTH_HEADER_KEY_SECRET = "X-Naver-Client-Secret";

  private final NaverApiProperties properties;

  @Override
  public List<NaverPlaceDto> getPlaces(String query) {
    String uri =
        properties.getBaseUrl()
            + UriComponentsBuilder.fromUriString(properties.getApi().get("places"))
                .buildAndExpand(query);

    SearchListResponse<NaverPlaceDto> response;

    HttpHeaders headers = new HttpHeaders();
    headers.set(AUTH_HEADER_KEY_ID, properties.getClientId());
    headers.set(AUTH_HEADER_KEY_SECRET, properties.getClientSecret());

    try {
      response = new RestTemplate().exchange(
                  uri,
                  HttpMethod.GET,
                  new HttpEntity<>(headers),
                  new ParameterizedTypeReference<SearchListResponse<NaverPlaceDto>>() {})
              .getBody();
    } catch (Exception ex) {
      throw new ClientException(ex);
    }

    if (response != null) {
      return response.get();
    } else {
      log.warn("> external api(Naver) returns empty response.");
      return Collections.emptyList();
    }
  }

}
