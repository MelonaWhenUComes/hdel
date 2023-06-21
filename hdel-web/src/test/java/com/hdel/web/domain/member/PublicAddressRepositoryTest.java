package com.hdel.web.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("local")
class PublicAddressRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DisplayName("1. 유저 데이터 생성하기")
    @Test
    void test_1(){
        String encPassword = passwordEncoder.encode("test_password");
        Member memberEntity = Member.builder()
                .userId("test_user")
                .userPw(encPassword)
                .userName("테스트유저")
                .build();

        Member savedMember = memberRepository.save(memberEntity);
        assertThat(memberEntity.getUserId()).isEqualTo(savedMember.getUserId());
    }
}
