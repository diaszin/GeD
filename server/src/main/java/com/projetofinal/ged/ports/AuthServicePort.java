package com.projetofinal.ged.ports;

public interface AuthServicePort {
    String generateToken(String email);
    boolean validate(String token);
    String getEmail(String token);
}
