package com.example.spring.test.member.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank//빈 문자열 방지
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
