package com.hdel.service.document.dto;

import com.hdel.service.document.entity.user.Role;
import com.hdel.service.document.entity.user.UserRoleMapping;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleMappingDto {
    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String role;

    public static UserRoleMappingDto from(UserRoleMapping userRoleMapping) {
        if(userRoleMapping == null) return null;

        return UserRoleMappingDto.builder()
                .role(userRoleMapping.getRole())
                .build();
    }
}