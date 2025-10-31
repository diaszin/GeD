package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.dtos.UserCreateDTO;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);


    List<User> toDomainUser(List<JPAUserEntity> entity);

    @Mapping(target = "id", ignore = true)
    JPAUserEntity toJPAEntity(User user);

    User toDomainUser(JPAUserEntity entity);

    @Mapping(source = "name", target = "fullName")
    User createDTOToDomainUser(UserCreateDTO dto);
}
