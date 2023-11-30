package com.devjaewon.securityproject.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
    private final String SECRET_KEY = "REQUIRE PRIVATE SECURITY KEY!!!!";

    public JwtProvider() {}

    public String createAccessToken(String user, List<String> roles) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", roles);

        return createToken(user, System.currentTimeMillis() + 1000 * 60 * 10, claims);
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String userId, long exprieTime, Map<String, ?> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(userId)
                .expiration(new Date(exprieTime))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
    }
}