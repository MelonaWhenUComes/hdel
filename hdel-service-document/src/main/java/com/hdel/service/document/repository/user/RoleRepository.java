package com.hdel.service.document.repository.user;

import com.hdel.service.document.entity.user.Role;
import com.hdel.service.document.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByRole(String role);

}
