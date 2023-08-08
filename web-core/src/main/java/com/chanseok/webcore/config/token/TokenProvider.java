package com.chanseok.webcore.config.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class TokenProvider {
    private final TokenProperty tokenProperty;
    private Key key;

    public TokenProvider(TokenProperty tokenProperty) {
        this.tokenProperty = tokenProperty;
    }

    @PostConstruct
    public void init() {
        byte[] bytes = Decoders.BASE64.decode(tokenProperty.getSecretKey());
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(String email) {
        return this.buildToken(email, tokenProperty.getExpiration());
    }

    private String buildToken(String email, Long expiration) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String email) {
        final String tokenEmail = this.extractEmail(token);
        return tokenEmail.equals(email) && !isTokenExpired(token);
    }

    public String extractEmail(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> function) {
        Claims claims = this.extractAllClaims(token);
        return function.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
