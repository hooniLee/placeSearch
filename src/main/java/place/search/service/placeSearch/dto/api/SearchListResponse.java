package place.search.service.placeSearch.dto.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@NoArgsConstructor
public class SearchListResponse<T> {
  @JsonAlias({"documents", "items"})
  private List<T> data;

  public List<T> get() {
    return data;
  }
}
