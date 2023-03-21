package place.search.service.placeSearch.dto.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  SUCCESS_OK(200, "OK"),

  SERVER_ERROR(500, "Internal Server Error");
  private final int code;
  private final String message;
}
