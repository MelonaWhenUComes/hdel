package com.hdel.service.document.repository.user;

import com.hdel.service.document.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
