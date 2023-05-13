package com.theduckfood.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final String secret = "AJFE8GWBEGB873TI90FSVNEIJBEIGWHGIUslfwigkh3gifhewuyyreurywirwyrweureryeuywty43f2u375385ty";
    public static String generateJWTToken(String email, String role) {
        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 30L *24*60*60*1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static Map<String, Object> getPayloadFromJWTToken(String token) {
        Map<String, Object> payload = new HashMap<>();
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            payload.put("email", claims.get("email").toString());
            payload.put("role", claims.get("role").toString());
            payload.put("expiration", claims.getExpiration());
        } catch (Exception e) {
            return null;
        }
        return payload;
    }
}
