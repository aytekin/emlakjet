package com.emlakjet.demo.security.service;

import com.emlakjet.demo.security.jwt.JwtHelper;
import com.emlakjet.demo.security.request.LoginRequest;
import com.emlakjet.demo.security.response.AccessTokenResponse;
import com.emlakjet.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtHelper jwtHelper;
    private UserService userService;

    public AccessTokenResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword()));

        return AccessTokenResponse.builder()
                .accessToken(jwtHelper.createJwtToken(userService.findByEmail(email),true))
                .build();
    }
}
