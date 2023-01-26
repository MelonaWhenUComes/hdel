package com.hdel.redis.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RedisTestController {
    private final RedisTemplate<String, String> redisTemplate;

    @PostMapping("/data")
    public ResponseEntity<String> setRedisData(
            @RequestBody(required = true) Map<String,String> map) throws Exception{

        redisTemplate.opsForValue().set(map.get("key"), map.get("value"));

        return new ResponseEntity<>("정상 등록", HttpStatus.CREATED);
    }

    @GetMapping("/data")
    public ResponseEntity<String> getRedisData(
            @RequestParam(required = true) String key){

        return new ResponseEntity<>(redisTemplate.opsForValue().get(key), HttpStatus.OK);
    }

    @GetMapping("/test/setSession")
    public String testSetSession(HttpSession session) {
        session.setAttribute("id", "2033674");
        session.setAttribute("name", "장영춘");

        return "test";
    }

    @GetMapping("/test/getSession")
    public String testGetSession(HttpSession session) {
        String id = (String)session.getAttribute("id");
        String name = (String)session.getAttribute("name");

        return "id : " + id + " / name : " + name;
    }
}
