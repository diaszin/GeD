package com.projetofinal.ged.ports;

public interface PasswordCriptographyPort {
    String hash(String password);

    Boolean compare(String password, String other_password);
}
