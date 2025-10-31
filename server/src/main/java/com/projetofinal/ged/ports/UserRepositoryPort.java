package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.entities.JPAUserEntity;

import java.util.Date;
import java.util.List;

public interface UserRepositoryPort {
    List<JPAUserEntity> getAll();

    JPAUserEntity create(JPAUserEntity entity);

    JPAUserEntity getByEmail(String email);
}
