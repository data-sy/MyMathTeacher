package com.mmt.diagnosis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("/*") // 외부에서 들어오는 모든 url 허용
                .allowedOrigins("http://localhost:8080", "http://15.164.232.32:8080",
                        "http://localhost:5173",
                        "https://localhost:5173",
                        "https://127.0.0.1:5173"
                )
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true) // 클라이언트에서 쿠키를 받기 위해
                .maxAge(3600);
    }
}
