package com.Luna.b2b_reconciliation.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "ClaveSecretaMuyLargaParaQueNadieLaAdivine123456789";

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)                          // antes: .setSubject()
                .issuedAt(new Date())                       // antes: .setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + 86400000))  // antes: .setExpiration()
                .signWith(getKey())                         // antes: .signWith(Algorithm, secret)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getKey())                       // antes: .setSigningKey()
                .build()
                .parseSignedClaims(token)                   // antes: .parseClaimsJws()
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}