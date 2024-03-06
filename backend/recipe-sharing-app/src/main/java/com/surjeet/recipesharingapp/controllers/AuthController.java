package com.surjeet.recipesharingapp.controllers;

import com.surjeet.recipesharingapp.config.JwtProvider;
import com.surjeet.recipesharingapp.models.User;
import com.surjeet.recipesharingapp.repository.UserRepository;
import com.surjeet.recipesharingapp.request.LoginRequest;
import com.surjeet.recipesharingapp.response.AuthResponse;
import com.surjeet.recipesharingapp.service.CustomUserDetailService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{
        String email =user.getEmail();
        String password=user.getPassword();
        String fullName=user.getFullName();

        User isExistingEmail=userRepository.findByEmail(email);
        if(isExistingEmail!=null){
            throw new Exception("Email is already used with another acount");
        }

        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);

        User savedUser=userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);
//
        AuthResponse res=new AuthResponse();
//
        res.setJwt(token);
        res.setMessage("Sign up success");


        return res;

    }

    @PostMapping("/login")
    public AuthResponse signInHandler(@RequestBody LoginRequest loginRequest){
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();

        Authentication authentication=authenticate(username,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse();

        res.setJwt(token);
        res.setMessage("Login success");
        return res;
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails=customUserDetailService.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("User not found");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }


}
