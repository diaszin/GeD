package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.FilePartialUpdateDTO;
import com.projetofinal.ged.application.dtos.in.FilesInFolderReadDTO;
import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.UploadedFile;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
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


    @Mapping(source = "createdBy.fullName", target = "createdBy.name")
    List<FilesInFolderReadDTO> entityToFileinFolderReadDTO(List<File> files);

    File partialUpdateDTOToDomain(FilePartialUpdateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void modifiedFileToFile(File modifiedFile, @MappingTarget File file);
}
