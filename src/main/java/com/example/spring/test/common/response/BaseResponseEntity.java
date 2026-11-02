package com.example.spring.test.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponseEntity<T> {

    private final int status;
    private final String message;
    private final T data;

    public BaseResponseEntity(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponseEntity<T> ok(T data, String message) {
        return new BaseResponseEntity<>(HttpStatus.OK.value(), message, data);
    }

    public static <T> BaseResponseEntity<T> ok(T data) {
        return ok(data, "요청에 성공했습니다.");
    }

    public static BaseResponseEntity<Void> ok() {
        return ok(null);
    }

}
