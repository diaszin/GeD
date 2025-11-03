package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.application.dtos.UserCreateDTO;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import com.projetofinal.ged.infra.mappers.UserMapper;
import com.projetofinal.ged.ports.AuthServicePort;
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
    private final AuthServicePort authService;

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
    public String login(User user) {


        Optional<JPAUserEntity> foundUser = Optional.ofNullable(this.userRepository.getByEmail(user.getEmail()));

        if (foundUser.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }

        boolean isPasswordMatch = this.passwordCripto.compare(user.getPassword(), foundUser.get().getPassword());

        if(!isPasswordMatch){
            throw new RuntimeException("Senha incorreta");
        }

        return this.authService.generateToken(foundUser.get().getEmail());
    }

    @Override
    public User getByEmail(String email){
        Optional<JPAUserEntity> jpaUserEntity = Optional.ofNullable(this.userRepository.getByEmail(email));

        return jpaUserEntity.map(userMapper::toDomainUser).orElse(null);

    }

}
