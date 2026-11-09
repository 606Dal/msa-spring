package com.example.spring.test.member.controller;

import com.example.spring.test.common.response.BaseResponseEntity;
import com.example.spring.test.member.request.ChangeEmailRequest;
import com.example.spring.test.member.request.ChangePasswordRequest;
import com.example.spring.test.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PatchMapping("/email")
    public BaseResponseEntity<Void> changeEmail(@Valid @RequestBody ChangeEmailRequest request) {
        memberService.changeEmail(request);
        return BaseResponseEntity.ok();
    }

    @PatchMapping("/password")
    public BaseResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        memberService.changePassword(request);
        return BaseResponseEntity.ok();
    }
}
