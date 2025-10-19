package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.entities.JPAUserEntity;
import com.projetofinal.ged.mappers.UserMapper;
import com.projetofinal.ged.ports.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class JPAUserRepositoryAdapter implements UserRepositoryPort {

    @Autowired
    private SpringUserRepository repository;

    @Override
    public List<User> getAll() {
        UserMapper mapper = UserMapper.instance;
        List<JPAUserEntity> users = this.repository.findAll();
        return mapper.toDomainUser(users);
    }

    @Override
    public User create(String email, String password, String fullName, Date birthdayDate) {
        User userData = new User(fullName, email, password, birthdayDate);
        JPAUserEntity jpaUserEntity = UserMapper.instance.toJPAEntity(userData);

        JPAUserEntity recentCreatedUser = this.repository.save(jpaUserEntity);

        return UserMapper.instance.toDomainUser(recentCreatedUser);
    }
}
