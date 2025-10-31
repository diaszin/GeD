package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.dtos.UserCreateDTO;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import com.projetofinal.ged.infra.mappers.UserMapper;
import com.projetofinal.ged.ports.PasswordCriptographyPort;
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
    private final PasswordCriptographyPort passwordCripto;

    @Override
    public List<User> getAll() {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");

            String dateInString = "22-01-2015";
            Date date = formatter.parse(dateInString);

            UserMapper mapper = UserMapper.instance;
            List<JPAUserEntity> allUsers = this.userRepository.getAll();

            return mapper.toDomainUser(allUsers);
        }
        catch (ParseException exception){
            throw  new RuntimeException("Data de aniversário inválido");
        }
    }

    @Override
    public User create(UserCreateDTO userCreateDTO) {
        UserMapper mapper =UserMapper.instance;

        User user =  mapper.createDTOToDomainUser(userCreateDTO);

        String hashedPassword = this.passwordCripto.hash(user.getPassword());
        user.setPassword(hashedPassword);

        JPAUserEntity jpaUserEntity = mapper.toJPAEntity(user);
        JPAUserEntity recentCreatedUser = this.userRepository.create(jpaUserEntity);

        return mapper.toDomainUser(recentCreatedUser);
    }


}
