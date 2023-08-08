package com.chanseok.rediscore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperty {
    private final String host;
    private final int port;

    public RedisProperty(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
