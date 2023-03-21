package place.search.service.placeSearch.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class NaverPlaceDto implements Place {
  private String title;
  private String link;
  private String category;
  private String description;
  private String telephone;
  private String address;
  private String roadAddress;

  @Override
  public PlaceData toPlaceData() {
    //이름만 사용, 정렬을 위해서 순서 전달
    return new PlaceData(title,"2");
  }
}
