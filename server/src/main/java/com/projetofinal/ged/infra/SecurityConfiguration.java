package com.projetofinal.ged.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JWTConfigurationFilter jwtConfigurationFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    authorize -> authorize
                            .requestMatchers("/user/**").permitAll() // permite TODAS as rotas e métodos
                            .requestMatchers("/user/login").permitAll()
                            .anyRequest().authenticated()
            )
                .addFilterBefore(jwtConfigurationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
