package com.hdel.service.document.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hdel.service.document.entity.user.Role;
import com.hdel.service.document.entity.user.User;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

/*
    @Column(nullable = false, name="CREATED_ID")
    private long createdId;

    @Column(nullable = true, name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(nullable = false, name="MODIFIED_ID")
    private long modifiedId;

    @Column(nullable = true, name="MODIFIED_AT")
    private LocalDateTime modifiedAt;
*/

    @NotNull
    @Size(min = 3, max = 100)
    private String role;

    @NotNull
    @Size(min = 3, max = 100)
    private String roleName;

    public static RoleDto from(Role role) {
        if(role == null) return null;

        return RoleDto.builder()
                .role(role.getRole())
                .roleName(role.getRoleName())
                .build();
    }
}