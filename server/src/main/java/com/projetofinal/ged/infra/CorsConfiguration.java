package com.projetofinal.ged.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Value("${ALLOWED_APPLICATIONS}")
    private String allowedApps;

    public void addCorsMappings(CorsRegistry registry) {
        String[] apps = allowedApps.split(",");
        
        registry.addMapping("/**").allowedOrigins(apps).allowedMethods("*");
    }
}
