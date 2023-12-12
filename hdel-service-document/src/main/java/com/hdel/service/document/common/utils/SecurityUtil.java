package com.hdel.service.document.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {
    //getCurrentUsername method : Security Context의 Authentication 객체로 username 리턴

    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //JwtFilter의 doFilter 메소드에서 Request가 들어올때 SecurityContext에 Authentication 객체를 저장해서 사용
    }
}
