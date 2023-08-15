package com.chanseok.rediscore.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final long DEFAULT_TIME_OUT = 30;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addCache(String key, String value) {
        redisTemplate.opsForValue().set(key, value, DEFAULT_TIME_OUT, TimeUnit.MINUTES);
    }

    public String getCache(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
