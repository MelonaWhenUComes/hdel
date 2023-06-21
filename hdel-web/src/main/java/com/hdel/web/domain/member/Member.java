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

    @Column(nullable = false, name="USER_ID")
    private String userId;
    @Column(nullable = false, name="USER_PW")
    private String userPw;

    @Column(nullable = false, name="USER_NAME")
    private String userName;

    @Column(nullable = false, name="USER_EMAIL")
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name="ROLE")
    private Role role;

    @Builder
    public Member(String userId, String userPw, String userName, String userEmail, Role role) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public Member update(String userName) {
        this.userName = userName;

        return this;
    }
}
