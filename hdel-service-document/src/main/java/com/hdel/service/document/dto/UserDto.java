package com.hdel.service.document.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hdel.service.document.entity.user.Role;
import com.hdel.service.document.entity.user.User;
import com.hdel.service.document.entity.user.UserRoleMapping;
import lombok.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 100)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String email;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @Size(min = 3, max = 100)
    private String nickname;

    @NotNull
    @Size(min = 3, max = 50)
    private String role;

    private Set<UserRoleMappingDto> userRoleMappingDtoSet;

    @Size(min = 3, max = 200)
    private String address;

    @Size(min = 3, max = 200)
    private String addressDetail;

//    public static UserDto from(User user, Set<UserRoleMappingDto> userRoleMappingDtoSet) {
//        if(user == null) return null;
//
//        return UserDto.builder()
//                .name(user.getName())
//                .nickname(user.getNickName())
//                .userRoleMappingDtoSet(userRoleMappingDtoSet)
//                .email(user.getEmail())
//                .address(user.getAddress())
//                .addressDetail(user.getAddressDetail())
//                .build();
//    }
    public static UserDto from(User user) {
        if(user == null) return null;

        return UserDto.builder()
                .name(user.getName())
                .nickname(user.getNickName())
                .email(user.getEmail())
                .address(user.getAddress())
                .addressDetail(user.getAddressDetail())
                .build();
    }

}