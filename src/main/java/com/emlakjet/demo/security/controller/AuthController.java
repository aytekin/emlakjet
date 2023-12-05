package com.emlakjet.demo.security.controller;

import com.emlakjet.demo.security.request.LoginRequest;
import com.emlakjet.demo.security.response.AccessTokenResponse;
import com.emlakjet.demo.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth/")
public class AuthController {
    private final AuthService service;

    @Operation(summary = "login to the system with credentials", description = "Role constraints : PUBLIC")
    @PostMapping("login")
    public AccessTokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

}
