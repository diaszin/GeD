package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.dtos.UserCreateDTO;
import com.projetofinal.ged.ports.UserRepositoryPort;
import com.projetofinal.ged.ports.UserServicePort;
import lombok.AllArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class UserServiceAdapter implements UserServicePort {

    private final UserRepositoryPort userRepository;

    @Override
    public List<User> getAll() {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");

            String dateInString = "22-01-2015";
            Date date = formatter.parse(dateInString);

            return this.userRepository.getAll();
        }
        catch (ParseException exception){
            throw  new RuntimeException("Data de aniversário inválido");
        }
    }

    @Override
    public User create(UserCreateDTO userCreateDTO) {
        return this.userRepository.create(userCreateDTO.email, userCreateDTO.password, userCreateDTO.name, userCreateDTO.birthdayDate);
    }


}
