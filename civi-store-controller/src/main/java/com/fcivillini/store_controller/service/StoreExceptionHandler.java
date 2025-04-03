package com.fcivillini.store_controller.service;

import com.fcivillini.store_interface.exc.StoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class StoreExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {StoreException.class})
    public ResponseEntity<String> handleFididocException(StoreException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
    }

}

