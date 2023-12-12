package com.hdel.service.document.service.user;

import com.hdel.service.document.dto.UserDto;
import com.hdel.service.document.entity.user.UserRoleMapping;
import com.hdel.service.document.repository.user.RoleRepository;
import com.hdel.service.document.repository.user.UserRepository;
import com.hdel.service.document.repository.user.UserRoleMappingRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.RoleNotFoundException;

public class UserService {
    //UserService 는 userRepository, passwordEncorder 주입

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserRoleMappingRepository userRoleMappingRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //signup 유저가입
    public UserDto signUp(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 사용자");
        }

        //권한 만들고 유저정보 만들고 저장
        UserRoleMapping userRoleMapping = UserRoleMapping.builder()
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .createdAt()
        //getUserWithAuthorities - username 기준 정보 가져옴

        //getMyUserWithAuthorities - SecurityContext에 저장된 username만 가져옴
    }

}
