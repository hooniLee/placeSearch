package place.search.service.placeSearch.exception;

import lombok.Getter;
import place.search.service.placeSearch.dto.common.ErrorCode;

@Getter

public class ClientException extends RuntimeException{
    private final ErrorCode errorCode = ErrorCode.SERVER_ERROR;

    public ClientException(Throwable throwable) {
        super(ErrorCode.SERVER_ERROR.getMessage(), throwable);
    }
}
