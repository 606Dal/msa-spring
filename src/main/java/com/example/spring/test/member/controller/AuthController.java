package com.example.spring.test.member.controller;

import com.example.spring.test.common.response.BaseResponseEntity;
import com.example.spring.test.member.request.LoginRequest;
import com.example.spring.test.member.request.SignUpRequest;
import com.example.spring.test.member.response.LoginResponse;
import com.example.spring.test.member.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirements
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);

        return BaseResponseEntity.ok();
    }

    @PostMapping("/login")
    public BaseResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return BaseResponseEntity.ok(loginResponse);
    }
}
