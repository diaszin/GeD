package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.User;

public interface AuthServicePort {
    String generateToken(String email);
    boolean validate(String token);
    String getEmail(String token);
}
