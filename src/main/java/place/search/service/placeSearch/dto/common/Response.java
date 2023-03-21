package place.search.service.placeSearch.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
  private ResponseHeader header;
  private T body;

  public static <T> Response<T> success(T body) {
    ResponseHeader header = new ResponseHeader( 200, "OK");
    return new Response<>(header, body);
  }

  public static <T> Response<T> fail(ErrorCode errorCode) {
    ResponseHeader header =
        new ResponseHeader(errorCode.getCode(), errorCode.getMessage());
    return new Response<>(header, null);
  }

  public static <T> Response<T> fail(int resultCode, String resultMessage) {
    ResponseHeader header = new ResponseHeader(resultCode, resultMessage);
    return new Response<>(header, null);
  }
}
