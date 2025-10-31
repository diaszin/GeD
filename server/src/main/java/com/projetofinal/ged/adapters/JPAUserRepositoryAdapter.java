package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import com.projetofinal.ged.infra.mappers.UserMapper;
import com.projetofinal.ged.ports.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class JPAUserRepositoryAdapter implements UserRepositoryPort {

    @Autowired
    private SpringUserRepository repository;

    @Override
    public List<JPAUserEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public JPAUserEntity create(JPAUserEntity entity) {
         return this.repository.save(entity);
    }
}
