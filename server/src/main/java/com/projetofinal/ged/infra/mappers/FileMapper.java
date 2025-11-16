package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.FilesInFolderReadDTO;
import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.UploadedFile;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FileMapper {
    FileMapper instance = Mappers.getMapper(FileMapper.class);

    @Mapping(source = "createdBy", target = "user")
    @Mapping(source = "filePath", target = "generatedFilename")
    JPAFileEntity domainToEntity(File file);

    @Mapping(source = "user", target = "createdBy")
    @Mapping(source = "generatedFilename", target = "filePath")
    File entityToDomain(JPAFileEntity entity);

    @Mapping(source = "user", target = "createdBy")
    List<File> entityToDomain(List<JPAFileEntity> entity);

    @Mapping(source = "path", target = "filePath")
    File uploadFileDomainToFileDomain(UploadedFile uploadedFile);


    List<FilesInFolderReadDTO> entityToFileinFolderReadDTO(List<File> files);
}
