package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.application.dtos.in.UserCreateDTO;

import java.util.List;

public interface UserServicePort {
    List<User> getAll();

    void create(UserCreateDTO userCreateDTO);

    String login(User user);
    User getByEmail(String email);
}
