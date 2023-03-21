package place.search.service.placeSearch.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHeader {

  private int resultCode;
  private String resultMessage;
}
