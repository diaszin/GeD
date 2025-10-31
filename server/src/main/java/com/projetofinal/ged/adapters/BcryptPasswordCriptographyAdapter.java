package com.projetofinal.ged.adapters;

import com.projetofinal.ged.ports.PasswordCriptographyPort;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.crypto.SecretKeyFactory;
import java.security.SecureRandom;

public class BcryptPasswordCriptographyAdapter implements PasswordCriptographyPort {

    @Override
    public String hash(String password) {
        String salt = BCrypt.gensalt();

        return BCrypt.hashpw(password, salt);
    }

    @Override
    public Boolean compare(String purePassword, String hashedPassword) {
        return BCrypt.checkpw(purePassword, hashedPassword);
    }
}
