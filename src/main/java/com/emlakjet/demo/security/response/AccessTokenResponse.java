package com.emlakjet.demo.security.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccessTokenResponse {
    private String accessToken;
}
