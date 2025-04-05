package com.fcivillini.store_interface.exc;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Accessors(chain = true)
public class StoreRuntimeException extends RuntimeException {

    protected final HttpStatus httpStatus;

    public StoreRuntimeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public StoreRuntimeException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }
}
