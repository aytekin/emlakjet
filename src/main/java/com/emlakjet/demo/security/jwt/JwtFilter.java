package com.emlakjet.demo.security.jwt;

import com.emlakjet.demo.common.exception.EmlakjetException;
import com.emlakjet.demo.common.exception.response.Code;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.emlakjet.demo.common.i18n.TranslationUtil.INVALID_TOKEN;
import static com.emlakjet.demo.common.i18n.Translator.toLocale;

public class JwtFilter extends GenericFilterBean {
    private final JwtHelper jwtHelper;

    public JwtFilter(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        String token = jwtHelper.resolveToken((HttpServletRequest) req);
        try {
            if (token != null && jwtHelper.validateToken(token)) {
                Authentication auth = jwtHelper.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            throw new EmlakjetException(Code.INVALID_JWT_TOKEN,toLocale(INVALID_TOKEN));
        }
        filterChain.doFilter(req, res);
    }
}
