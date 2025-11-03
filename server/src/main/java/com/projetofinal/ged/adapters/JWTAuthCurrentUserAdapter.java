package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.ports.AuthCurrentUserPort;
import com.projetofinal.ged.ports.UserServicePort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
public class JWTAuthCurrentUserAdapter implements AuthCurrentUserPort {

    private final UserServicePort userService;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String email = authentication.getName();

        return this.userService.getByEmail(email);
    }
}
