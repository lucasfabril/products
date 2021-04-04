package com.pedrone.products.controllers.exceptions.config;

import com.pedrone.products.controllers.exceptions.ArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ArgumentNotValidException> notValidException(MethodArgumentNotValidException ex){

        return ResponseEntity.badRequest()
                .body(ArgumentNotValidException.builder()
                        .status_code(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                        .build());
    }

}
