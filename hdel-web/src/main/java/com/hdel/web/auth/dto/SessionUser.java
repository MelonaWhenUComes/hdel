package com.hdel.web.auth.dto;

import com.hdel.web.domain.member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String userName;
    private String userEmail;

    public SessionUser(Member member) {
        this.userName = member.getUserName();
        this.userEmail = member.getUserEmail();
    }
}
