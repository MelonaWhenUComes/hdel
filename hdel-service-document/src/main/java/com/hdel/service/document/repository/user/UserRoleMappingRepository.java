package com.hdel.service.document.repository.user;

import com.hdel.service.document.entity.user.Role;
import com.hdel.service.document.entity.user.UserRoleMapping;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleMappingRepository extends CrudRepository<UserRoleMapping, Long> {
    Optional<UserRoleMapping> findById(Long id);

    Optional<UserRoleMapping> findByRole(String role);

    Optional<UserRoleMapping> findByEmail(String email);
    
}
