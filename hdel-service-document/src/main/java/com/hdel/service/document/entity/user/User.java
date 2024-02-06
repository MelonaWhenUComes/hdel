package com.hdel.service.document.entity.user;

import com.hdel.service.document.entity.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER")
@Data
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="ID")
    private long id;

    @Column(length = 100, nullable = false, name="EMAIL")
    private String email;

    @Column(length = 200, nullable = false, name="NAME")
    private String name;

    @Column(nullable = false, name="USER_NAME")
    private String password;

//    @Column(nullable = false, name="NAME")
//    private String role;

    @Column(length = 200, name="NICK_NAME")
    private String nickName;

    @Column(nullable = true, name="ADDRESS")
    private String address;

    @Column(nullable = true, name="ADDRESS_DETAIL")
    private String addressDetail;

    @Column(nullable = true, name="CREATED_ID")
    private long createdId;

    @Column(nullable = true, name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(nullable = true, name="MODIFIED_ID")
    private long modifiedId;

    @Column(nullable = true, name="MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Builder
    public User(String email, String name, String password, String nickName, String address, String addressDetail, long createdId, LocalDateTime createdAt, long modifiedId, LocalDateTime modifiedAt) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.address = address;
        this.addressDetail = addressDetail;
        this.createdId = createdId;
        this.createdAt = createdAt;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
    }

}
