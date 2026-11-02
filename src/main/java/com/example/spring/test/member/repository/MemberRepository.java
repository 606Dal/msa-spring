package com.example.spring.test.member.repository;

import com.example.spring.test.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일 있는 지 확인(중복 체크 용)
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
    Optional<Member> findByUuid(String uuid);

}
