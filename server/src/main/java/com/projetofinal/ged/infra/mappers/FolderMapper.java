package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.FolderCreateDTO;
import com.projetofinal.ged.application.dtos.in.FolderPartialUpdateDTO;
import com.projetofinal.ged.application.dtos.out.FolderReadDTO;
import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {FileMapper.class, UserMapper.class})
public interface FolderMapper {
    FolderMapper instance = Mappers.getMapper(FolderMapper.class);

    JPAFolderEntity domainToEntity(Folder folder);


    Folder entityToDomain(JPAFolderEntity entity);

    Folder createDTOToDomain(FolderCreateDTO dto);

    @Mapping(target = "project.folders", ignore = true)
    List<Folder> entityToDomain(List<JPAFolderEntity> entityList);

    List<FolderReadDTO> domainToFolderReadDTO(List<Folder> domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Folder partialUpdateDTOToFolder(FolderPartialUpdateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFolder( Folder modifiedFolder, @MappingTarget  JPAFolderEntity folder);

}
