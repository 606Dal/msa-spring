package com.example.spring.test.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChangePasswordRequest {

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
