package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.dtos.UserCreateDTO;
import com.projetofinal.ged.dtos.UserLoginDTO;
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
import java.util.Optional;

@AllArgsConstructor
public class UserServiceAdapter implements UserServicePort {

    private final UserRepositoryPort userRepository;
    private final PasswordCriptographyPort passwordCripto;
    private final UserMapper userMapper = UserMapper.instance;
    @Override
    public List<User> getAll() {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");

            String dateInString = "22-01-2015";
            Date date = formatter.parse(dateInString);

            ;
            List<JPAUserEntity> allUsers = this.userRepository.getAll();

            return userMapper.toDomainUser(allUsers);
        }
        catch (ParseException exception){
            throw  new RuntimeException("Data de aniversário inválido");
        }
    }

    @Override
    public void create(UserCreateDTO userCreateDTO) {

        User user =  userMapper.createDTOToDomainUser(userCreateDTO);

        String hashedPassword = this.passwordCripto.hash(user.getPassword());
        user.setPassword(hashedPassword);

        JPAUserEntity jpaUserEntity = userMapper.toJPAEntity(user);

        this.userRepository.create(jpaUserEntity);
    }

    @Override
    public Boolean login(UserLoginDTO dto) {
        User currentUser = userMapper.loginDTOToDomainUser(dto);

        Optional<JPAUserEntity> foundUser = Optional.ofNullable(this.userRepository.getByEmail(currentUser.getEmail()));

        if (foundUser.isEmpty()){
            return false;
        }

        return this.passwordCripto.compare(currentUser.getPassword(), foundUser.get().getPassword());
    }


}
