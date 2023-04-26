package com.hdel.web.domain.user;

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

    @Column(nullable = true, name="USER_ID")
    private String userId;
    @Column(nullable = true, name="USER_PW")
    private String userPw;

    @Column(nullable = true, name="USER_NAME")
    private String userName;

    @Column(nullable = true, name="USER_EMAIL")
    private String userEmail;

    @Column(name="ROLE")
    private Role role;

    @Builder
    public Member(String userId, String userPw, String userName, String userEmail) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
