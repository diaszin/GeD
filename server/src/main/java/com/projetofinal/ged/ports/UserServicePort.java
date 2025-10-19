package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.dtos.UserCreateDTO;

import java.util.List;

public interface UserServicePort {
    List<User> getAll();

    User create(UserCreateDTO userCreateDTO);
}
