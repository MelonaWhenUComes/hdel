package com.hdel.service.document.service.user;

import com.hdel.service.document.config.exception.BusinessExceptionHandler;
import com.hdel.service.document.dto.UserDto;
import com.hdel.service.document.entity.user.User;
import com.hdel.service.document.entity.user.UserRoleMapping;
import com.hdel.service.document.repository.user.RoleRepository;
import com.hdel.service.document.repository.user.UserRepository;
import com.hdel.service.document.repository.user.UserRoleMappingRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;

import static com.hdel.service.document.common.codes.ErrorCode.ROLE_NOT_EXIST;

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
    @Transactional
    public UserDto signUp(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new BusinessExceptionHandler(ROLE_NOT_EXIST);
        }

        //권한 만들고 유저정보 만들고 저장
        UserRoleMapping userRoleMapping = UserRoleMapping.builder()
                .role(userDto.getRole())
                .createdId(userDto.getId())
                .modifiedId(userDto.getId())
                .build();
        userRoleMappingRepository.save(userRoleMapping);

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .nickName(userDto.getNickname())
                .address(userDto.getAddress())
                .addressDetail(userDto.getAddressDetail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        //UserDto userDto1 = UserDto.from(userRepository.save(user));

        //Role 반환 추가 수정 필요
        return UserDto.from(userRepository.save(user));
        //getUserWithAuthorities - username 기준 정보 가져옴

        //getMyUserWithAuthorities - SecurityContext에 저장된 username만 가져옴
    }

}
