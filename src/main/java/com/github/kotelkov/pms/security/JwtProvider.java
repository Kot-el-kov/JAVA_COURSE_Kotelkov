package com.github.kotelkov.pms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:/database.properties")
@Component
public class JwtProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private String expiration;

    public String buildToken(String username){
        final Map<String,Object> claims = new HashMap<>();
        claims.put("typ","access");
        return Jwts.builder().setClaims(claims).
                setIssuedAt(Date.from(Instant.now())).setSubject(username).
                setExpiration(Date.from(Instant.now().plus(Duration.parse(expiration).toMinutes(), ChronoUnit.MINUTES))).
                signWith(SignatureAlgorithm.HS256,secretKey.getBytes(StandardCharsets.UTF_8)).compact();
    }

    public String getUsernameFromToken(String token){
        final Claims claims = Jwts.parser().
                setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).
                parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
