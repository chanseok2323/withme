package com.chanseok.webcore.config.token;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "study-with-me.token")
public class TokenProperty {
    private final String secretKey;
    private final Long expiration;
    private final RefreshTokenProperty refreshToken;

    public TokenProperty(String secretKey, Long expiration, RefreshTokenProperty refreshToken) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.refreshToken = refreshToken;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Long getExpiration() {
        return expiration;
    }

    public RefreshTokenProperty getRefreshToken() {
        return refreshToken;
    }

    public record RefreshTokenProperty(Long expiration) {}

}
