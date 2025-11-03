package com.projetofinal.ged.infra;

import com.projetofinal.ged.adapters.JWTAuthServiceAdapter;
import com.projetofinal.ged.ports.AuthServicePort;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class JWTConfigurationFilter implements Filter {
    private final AuthServicePort jwtAuth;

    public JWTConfigurationFilter(AuthServicePort jwtAuth) {
        this.jwtAuth = jwtAuth;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer")){
            String token = header.split(" ")[1];

            if(jwtAuth.validate(token)){
                String email = jwtAuth.getEmail(token);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, null);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
