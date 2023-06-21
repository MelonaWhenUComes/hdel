package com.hdel.web.config.auth.dto;

import com.hdel.web.domain.member.Member;
import com.hdel.web.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userName;
    private String userEmail;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String userName, String userEmail) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .userName((String)attributes.get("userName"))
                .userEmail((String)attributes.get("userEmail"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .userName("userName")
                .userEmail("userEmail")
                .role(Role.GUEST)
                .build();
    }
}
