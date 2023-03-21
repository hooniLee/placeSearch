package place.search.service.placeSearch.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoPlaceDto implements Place {

  private String id;
  @JsonProperty("place_name")
  private String placeName;

  @JsonProperty("category_name")
  private String categoryName;

  @JsonProperty("place_url")
  private String placeUrl;

  @JsonProperty("address_name")
  private String addressName;

  @JsonProperty("road_address_name")
  private String roadAddressName;

  private String phone;

  @Override
  public PlaceData toPlaceData() {
    //이름만 사용, 정렬을 위해서 순서 전달
    return new PlaceData(placeName ,"1");
  }
}
