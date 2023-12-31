package com.example.tripbros.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://172.20.10.2:19006") //프론트 주소 //TODO: 프론트 ip 동적 아이피라길래, ALL-ALLOW로 풀어둡니다.
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
                // .allowCredentials(true);
    }
}