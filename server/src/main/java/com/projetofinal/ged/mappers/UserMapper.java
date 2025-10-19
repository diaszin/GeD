package com.projetofinal.ged.mappers;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.entities.JPAUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);


    List<User> toDomainUser(List<JPAUserEntity> entity);


    JPAUserEntity toJPAEntity(User user);

    User toDomainUser(JPAUserEntity entity);
}
