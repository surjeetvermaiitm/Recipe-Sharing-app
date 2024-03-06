package com.surjeet.recipesharingapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.io.IOException;

@Service
public class JwtTokenValidator extends OncePerRequestFilter {




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(JwtConstant.JWT_HEADER);
//        System.out.println("myjwt:"+jwt);
        if(jwt!=null){
            jwt=jwt.substring(7);
            try{
                SecretKey key= Keys.hmacShaKeyFor(JwtConstant.JWT_SECTRET.getBytes());
//                SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//                SecretKey key=Keys.hmacShaKeyFor(
//                        Decoders.BASE64.decode(JwtConstant.JWT_SECTRET));
//                System.out.println("validate key"+key);
                Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
                String email=String.valueOf(claims.get("email"));
//                System.out.println("myjwtemail:"+email);
                Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,null);

                SecurityContextHolder.getContext().setAuthentication(authentication);


            }catch(Exception e){
                System.out.println("jwt error"+e);
                throw new BadCredentialsException("Invalid token ...");

            }
        }
        filterChain.doFilter(request,response);
    }
}
