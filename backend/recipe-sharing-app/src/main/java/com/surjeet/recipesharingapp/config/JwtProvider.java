package com.surjeet.recipesharingapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {

    private SecretKey key= Keys.hmacShaKeyFor(JwtConstant.JWT_SECTRET.getBytes());
//    private SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//    private SecretKey key=Keys.hmacShaKeyFor(
//        Decoders.BASE64.decode(JwtConstant.JWT_SECTRET));

    public String generateToken(Authentication auth){
        System.out.println("generate Key"+key);
        String jwt= Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("email",auth.getName())
                .signWith(key).compact();
        return jwt;
    }

    public String getEmailFromJwtToken(String jwt){
        jwt=jwt.substring(7);
        Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email=String.valueOf(claims.get("email"));



        return email;
    }
}
