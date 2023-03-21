package place.search.service.placeSearch.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceData implements Serializable, Comparable<PlaceData> {
  private String name;
  private String order;

  @Override
  public int compareTo(PlaceData o) {
    return this.getOrder().compareTo(o.getOrder());
  }
}
