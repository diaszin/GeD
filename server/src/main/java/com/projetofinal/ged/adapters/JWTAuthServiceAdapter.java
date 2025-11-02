package com.projetofinal.ged.adapters;

import com.projetofinal.ged.ports.AuthServicePort;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

public class JWTAuthServiceAdapter implements AuthServicePort {
    @Value("${AUTH_SECRET_KEY}")
    private String secretKey;



    @Override
    public String generateToken(String email) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        long EXPIRATION = 10000 * 60 * 60;

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validate(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public String getEmail(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
         return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
