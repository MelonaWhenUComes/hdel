package com.hdel.redis.Entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "user", timeToLive = 30) //만료시간 Seconds : default -1L
public class User {
    @Id
    private String id;
    private String name;
    private LocalDateTime createdAt;

    public User(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

}
