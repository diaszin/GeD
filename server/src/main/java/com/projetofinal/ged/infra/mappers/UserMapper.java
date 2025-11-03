package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.application.dtos.UserCreateDTO;
import com.projetofinal.ged.application.dtos.UserLoginDTO;
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

    @Mapping(target = "id", ignore = true)
    User toDomainUser(JPAUserEntity entity);


    @Mapping(source = "name", target = "fullName")
    @Mapping(target = "id", ignore = true)
    User createDTOToDomainUser(UserCreateDTO dto);

    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "birthdayDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    User loginDTOToDomainUser(UserLoginDTO dto);
}
