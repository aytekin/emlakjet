package com.emlakjet.demo.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtHelper jwtHelper;

    public JwtConfigurer(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public void init(HttpSecurity builder) throws Exception {
        JwtFilter customFilter = new JwtFilter(jwtHelper);
        builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtFilter customFilter = new JwtFilter(jwtHelper);
        builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
