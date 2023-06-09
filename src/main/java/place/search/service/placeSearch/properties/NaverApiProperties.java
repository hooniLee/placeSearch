package place.search.service.placeSearch.properties;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "openapi.naver")
@Component
public class NaverApiProperties {
  private String baseUrl;
  private String clientId;
  private String clientSecret;
  private Map<String, String> api;
}
