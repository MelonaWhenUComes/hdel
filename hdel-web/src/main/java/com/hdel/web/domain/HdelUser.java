package com.hdel.web.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "HDEL_USER")
public class HdelUser {
    @Id
    @Column(length = 20, nullable = false, name="USER_ID")
    private String userId;

    @Column(length = 256, nullable = true, name="USER_PW")
    private String userPw;

    @Column(length = 30, nullable = true, name="NAME")
    private String name;

    @Column(length = 50, nullable = true, name="EMAIL")
    private String email;

    @Column(length = 30, nullable = true, name="MOBILE_NO")
    private String mobileNo;

    @Column(length = 100, nullable = true, name="ADDRESS")
    private String address;

    @Column(length = 8, nullable = true, name="BIRTH_DATE")
    private String birthDate;

    @Column(length = 2, nullable = true, name="ACTIVE_YN")
    private String activeYn;

    @CreatedDate
    @Column(nullable = true, name="REGIST_DT")
    private LocalDateTime registDt;

    @Column(length = 20, nullable = true, name="REGIST_ID")
    private String registId;

    @LastModifiedDate
    @Column(nullable = true, name="MODIFY_DT")
    private LocalDateTime modifyDt;

    @Column(length = 20, name="MODIFY_ID")
    private String modifyId;

    @Builder
    public HdelUser(String userId, String userPw, String name, String email, String mobileNo
            , String address, String birthDate, String activeYn, LocalDateTime registDt
            , String registId, LocalDateTime modifyDt, String modifyId
        ) {
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.address = address;
        this.birthDate = birthDate;
        this.activeYn = activeYn;
        this.registDt = registDt;
        this.registId = registId;
        this.modifyDt = modifyDt;
        this.modifyId = modifyId;
    }
}
