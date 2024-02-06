package com.hdel.service.document.entity.user;

import com.hdel.service.document.repository.user.UserRepository;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER_ROLE_MAPPING")
@Data
@Getter
public class UserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="ID")
    private long id;

    @Column(nullable = false, name="NAME")
    private String role;

    @Column(nullable = true, name="CREATED_ID")
    private long createdId;

//    @Column(nullable = true, name="CREATED_AT")
//    private LocalDateTime createdAt;

    @Column(nullable = true, name="MODIFIED_ID")
    private long modifiedId;

//    @Column(nullable = true, name="MODIFIED_AT")
//    private LocalDateTime modifiedAt;

    @Builder
    public UserRoleMapping(String role, long createdId, long modifiedId) {
        this.role = role;
        this.createdId = createdId;
        this.modifiedId = modifiedId;
    }
/*
    public UserRoleMapping(String email, String role, long createdId, LocalDateTime createdAt,long modifiedId, LocalDateTime modifiedAt) {
        this.email = email;
        this.role = role;
        this.createdId = createdId;
        this.createdAt = createdAt;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
    }
*/



}
