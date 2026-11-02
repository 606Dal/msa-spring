package com.example.spring.test.security.User;

import com.example.spring.test.common.exception.CustomExcption;
import com.example.spring.test.common.exception.ErrorCode;
import com.example.spring.test.member.entity.Member;
import com.example.spring.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new CustomExcption(ErrorCode.MEMBER_NOT_FOUND));
//
//        return User.builder()
//                .username(member.getEmail())
//                .password(member.getPassword())
//                .roles(member.getRole().name())
//                .build();
//    }
    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new CustomExcption(ErrorCode.MEMBER_NOT_FOUND));

        return User.builder()
                .username(member.getUuid())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }
}
