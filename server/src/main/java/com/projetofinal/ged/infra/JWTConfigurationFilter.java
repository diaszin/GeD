package com.projetofinal.ged.infra;

import com.projetofinal.ged.ports.AuthServicePort;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;


@Component
public class JWTConfigurationFilter extends OncePerRequestFilter {
    private final AuthServicePort jwtAuth;
    private final HandlerExceptionResolver resolver;

    public JWTConfigurationFilter(AuthServicePort jwtAuth, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.jwtAuth = jwtAuth;
        this.resolver = resolver;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        try {
            if (header != null && header.startsWith("Bearer")) {
                String token = header.split(" ")[1];

                if (jwtAuth.validate(token)) {
                    String email = jwtAuth.getEmail(token);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, null);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}
