package com.example.spring.test.common.exception;

import lombok.Getter;

@Getter
public class CustomExcption extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomExcption(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
