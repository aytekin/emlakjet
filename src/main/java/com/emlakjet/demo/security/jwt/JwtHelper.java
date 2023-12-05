package com.emlakjet.demo.security.jwt;

import com.emlakjet.demo.common.exception.EmlakjetException;
import com.emlakjet.demo.common.exception.response.Code;
import com.emlakjet.demo.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import static com.emlakjet.demo.common.i18n.TranslationUtil.EXPIRED_JWT_TOKEN;
import static com.emlakjet.demo.common.i18n.Translator.toLocale;

@Component
@RequiredArgsConstructor
public class JwtHelper {
    @Value("${security.jwt.access-token.secret-key}")
    private String accessTokenSecretKey;
    @Value("${security.jwt.refresh-token.secret-key}")
    private String refreshTokenSecretKey;
    @Value("${security.jwt.access-token.expire-ms}")
    private long accessTokenExpirationMs;
    @Value("${security.jwt.refresh-token.expire-ms}")
    private long refreshTokenExpirationMs;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        accessTokenSecretKey = Base64.getEncoder().encodeToString(accessTokenSecretKey.getBytes());
        refreshTokenSecretKey = Base64.getEncoder().encodeToString(refreshTokenSecretKey.getBytes());
    }

    public String createJwtToken(UserDto userDto, boolean isAccessToken) {

        Date now = new Date(System.currentTimeMillis());
        Date validity = new Date(now.getTime() + (isAccessToken ? accessTokenExpirationMs : refreshTokenExpirationMs));
        return Jwts.builder()
                .setClaims(getClaims(userDto))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, isAccessToken ? accessTokenSecretKey : refreshTokenSecretKey)
                .compact();
    }

    private Claims getClaims(UserDto userDto) {
        Claims claims = Jwts.claims().setSubject(userDto.getEmail());
        claims.put("name",userDto.getName());
        claims.put("lastName",userDto.getLastname());
        claims.put("email",userDto.getEmail());
        claims.put("id",userDto.getId());

        return claims;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(accessTokenSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(accessTokenSecretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new EmlakjetException(Code.EXPIRED_JWT_TOKEN, toLocale(EXPIRED_JWT_TOKEN));
        }
    }
}
