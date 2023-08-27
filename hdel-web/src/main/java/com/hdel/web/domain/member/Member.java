package com.hdel.web.domain.member;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="TB_USER")
@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_ID")
    private String userId;
    @Column(name="USER_PW")
    private String userPw;

    @Column(nullable = false, name="USER_NAME")
    private String userName;

    @Column(nullable = false, name="USER_EMAIL")
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name="USER_ROLE")
    private Role userRole;

    @Builder
    public Member(String userId, String userPw, String userName, String userEmail, Role userRole) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public String getRoleKey() {
        return this.userRole.getKey();
    }

    public Member update(String userName) {
        this.userName = userName;

        return this;
    }
}
