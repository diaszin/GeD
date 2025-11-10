package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface FileMapper {
    FileMapper instance = Mappers.getMapper(FileMapper.class);

    @Mapping(source = "createdBy", target = "user")
    JPAFileEntity domainToEntity(File file);

    @Mapping(source = "user", target = "createdBy")
    File entityToDomain(JPAFileEntity entity);
}
