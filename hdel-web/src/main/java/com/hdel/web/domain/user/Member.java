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
}
