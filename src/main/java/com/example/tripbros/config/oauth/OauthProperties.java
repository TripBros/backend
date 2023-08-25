package com.example.tripbros.config.oauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("oauth")
public class OauthProperties {
    private String kakaoClientId;
    private String kakaoRedirectUri;
    private String kakaoClientSecret;
}
