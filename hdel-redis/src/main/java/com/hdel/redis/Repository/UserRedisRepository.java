package com.hdel.redis.Repository;

import com.hdel.redis.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<User, String> {
}
