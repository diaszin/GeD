package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.dtos.UserCreateDTO;
import com.projetofinal.ged.dtos.UserLoginDTO;

import java.util.List;

public interface UserServicePort {
    List<User> getAll();

    void create(UserCreateDTO userCreateDTO);

    Boolean login(UserLoginDTO dto);
}
