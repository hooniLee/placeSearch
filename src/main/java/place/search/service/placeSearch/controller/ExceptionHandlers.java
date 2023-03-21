package place.search.service.placeSearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import place.search.service.placeSearch.dto.common.ErrorCode;
import place.search.service.placeSearch.dto.common.Response;
import place.search.service.placeSearch.exception.ClientException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {


  @ExceptionHandler(ClientException.class)
  public ResponseEntity<Object> handleClientException(ClientException ex) {
    log.error(">> handleClientException(message={})", ex.getMessage());
    return ResponseEntity.ok(
            Response.fail(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(Exception ex) {
    log.error(">> handleException(message={})", ex.getMessage());
    return ResponseEntity.ok(
            Response.fail(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMessage()));
  }
}
