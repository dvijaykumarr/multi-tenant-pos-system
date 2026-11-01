package com.tap.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final int MIN_SECRET_BYTES = 32;

    private final SecretKey key;
    private final JwtParser parser;
    private final String issuer;
    private final long expirationMs;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Value("${jwt.expiration-ms}") long expirationMs,
                       @Value("${jwt.issuer}") String issuer) {

        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (secretBytes.length < MIN_SECRET_BYTES) {
            throw new IllegalStateException(
                    "jwt.secret must be at least " + MIN_SECRET_BYTES + " bytes long");
        }

        this.key = Keys.hmacShaKeyFor(secretBytes);
        this.issuer = issuer;
        this.expirationMs = expirationMs;
        this.parser = Jwts.parser()
                .verifyWith(key)
                .requireIssuer(issuer)
                .build();
    }

    public String generateToken(Authentication authentication) {
        Date now = new Date();

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .issuer(issuer)
                .subject(authentication.getName())
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expirationMs))
                .signWith(key)
                .compact();
    }

    /** Verifies signature, issuer and expiry. Throws JwtException on any failure. */
    public Claims parseClaims(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }

    /** Accepts either a raw token or a full "Bearer xxx" header value. */
    public String getEmailFromToken(String tokenOrHeader) {
        String token = tokenOrHeader.startsWith(BEARER_PREFIX)
                ? tokenOrHeader.substring(BEARER_PREFIX.length()).trim()
                : tokenOrHeader.trim();
        return parseClaims(token).get("email", String.class);
    }
}