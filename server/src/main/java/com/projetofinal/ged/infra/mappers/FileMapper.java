package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.UploadedFile;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {
    FileMapper instance = Mappers.getMapper(FileMapper.class);

    @Mapping(source = "createdBy", target = "user")
    @Mapping(source = "filePath", target = "generatedFilename")
    JPAFileEntity domainToEntity(File file);

    @Mapping(source = "user", target = "createdBy")
    File entityToDomain(JPAFileEntity entity);

    @Mapping(source = "path", target = "filePath")
    File uploadFileDomainToFileDomain(UploadedFile uploadedFile);
}
