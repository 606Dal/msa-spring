package com.example.spring.test.common.response;

import com.example.spring.test.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResposeEntity {

    private final int code;
    private final int status;
    private final String message;

    public ErrorResposeEntity(int code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public static ErrorResposeEntity from(ErrorCode errorCode) {
        return new ErrorResposeEntity(errorCode.getCode(),
                errorCode.getStatus().value(),
                errorCode.getMessage());
    }

    public static ErrorResposeEntity of(ErrorCode errorCode, String detail) {
        String message = errorCode.getMessage();
        if(detail != null && !detail.isBlank()) {
            message = message + " " + detail;
        }

        return new ErrorResposeEntity(
                errorCode.getCode(),
                errorCode.getStatus().value(),
                message);
    }
}
