package com.example.spring.test.member.service;

import com.example.spring.test.common.exception.CustomExcption;
import com.example.spring.test.common.exception.ErrorCode;
import com.example.spring.test.member.entity.Member;
import com.example.spring.test.member.repository.MemberRepository;
import com.example.spring.test.member.request.ChangeEmailRequest;
import com.example.spring.test.member.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void changeEmail(ChangeEmailRequest request) {
        Member member = currentMember();

        if (!request.getEmail().equals(member.getEmail())
                && memberRepository.existsByEmail(request.getEmail())) {
            throw new CustomExcption(ErrorCode.EMAIL_DUPLICATED);
        }

        member.changeEmail(request.getEmail());
    }

    public void changePassword(ChangePasswordRequest request) {
        Member member = currentMember();
        member.changePassword(passwordEncoder.encode(request.getPassword()));
    }

    private Member currentMember() {
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();

        return memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new CustomExcption(ErrorCode.MEMBER_NOT_FOUND));
    }
}
