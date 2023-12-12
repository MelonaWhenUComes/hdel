package com.hdel.service.document.entity.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ROLE")
@Data
@Getter
public class Role {
    @Id
    @Column(nullable = false, name="ROLE")
    private String role;

    @Column(nullable = false, name="ROLE_NAME")
    private String roleName;

    @Column(nullable = false, name="CREATED_ID")
    private long createdId;

    @Column(nullable = true, name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(nullable = false, name="MODIFIED_ID")
    private long modifiedId;

    @Column(nullable = true, name="MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Builder
    public Role(String role, String roleName, long createdId, LocalDateTime createdAt, long modifiedId, LocalDateTime modifiedAt) {
        this.role = role;
        this.roleName = roleName;
        this.createdId = createdId;
        this.createdAt = createdAt;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
    }

}
