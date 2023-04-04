package com.theduckfood.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static String generateJWTToken(String email) {
        String secret = "AJFE8GWBEGB873TI90FSVNEIJBEIGWHGIUslfwigkh3gifhewuyyreurywirwyrweureryeuywty43f2u375385ty";
        //Jwts.builder()
        return Jwts.builder()
                .claim("email", email)
                .claim("role", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 5*60*60*1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static void main(String[] args) {
        String token = generateJWTToken("minhtu@gmail.com");
        System.out.println(Jwts.parserBuilder()
                .setSigningKey("AJFE8GWBEGB873TI90FSVNEIJBEIGWHGIUslfwigkh3gifhewuyyreurywirwyrweureryeuywty43f2u375385ty")
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email"));
    }
}
