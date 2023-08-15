package com.chanseok.rediscore;

import com.chanseok.rediscore.config.RedisUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisUtilTest {

    @Autowired
    RedisUtil redisUtil;

    private static final String CACHE_KEY = "key1";

    @Test
    void cacheAddTest() {
        redisUtil.addCache(CACHE_KEY, "test1");
        String value = redisUtil.getCache(CACHE_KEY);

        Assertions.assertThat(value).isEqualTo("test1");
    }
}