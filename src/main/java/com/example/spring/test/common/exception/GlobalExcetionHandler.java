package com.example.spring.test.common.exception;

import com.example.spring.test.common.response.ErrorResposeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExcetionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResposeEntity> handleValidationException(
            MethodArgumentNotValidException exception) {

        String detail = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.info("validation Faild: {}", detail);

        return ResponseEntity
                .status(ErrorCode.INVALID_REQUEST.getStatus())
                .body(ErrorResposeEntity.of(ErrorCode.INVALID_REQUEST, detail));

    }

    @ExceptionHandler(CustomExcption.class)
    public ResponseEntity<ErrorResposeEntity> handleCustomExcption(CustomExcption exception) {
        ErrorCode errorCode = exception.getErrorCode();
        log.info("CustomException : {}", errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResposeEntity.from(errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResposeEntity> handleException(Exception exception) {
        log.info("Unhandled Exception", exception);

        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ErrorResposeEntity.from(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
