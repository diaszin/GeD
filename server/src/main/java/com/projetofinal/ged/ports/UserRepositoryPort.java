package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.User;

import java.util.Date;
import java.util.List;

public interface UserRepositoryPort {
    List<User> getAll();

    User create(String email, String password, String fullName, Date birthdayDate);
}
