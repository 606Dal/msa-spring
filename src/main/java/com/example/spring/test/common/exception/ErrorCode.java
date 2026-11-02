package com.example.spring.test.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST(3000, HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),

    PRODUCT_NOT_FOUND(4000, HttpStatus.BAD_REQUEST, "해당 상품이 존재하지 않습니다."),
    MEMBER_NOT_FOUND(4001, HttpStatus.BAD_REQUEST, "해당 유저가 존재하지 않습니다."),
    EMAIL_DUPLICATED(4002, HttpStatus.CONFLICT, "중복된 이메일 입니다."),

    LOGIN_FAILED(4003, HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED(4004, HttpStatus.UNAUTHORIZED, "인증이 필요한 서비스 입니다."),
    FORBIDDEN(4005, HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

    INTERNAL_SERVER_ERROR(5000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
