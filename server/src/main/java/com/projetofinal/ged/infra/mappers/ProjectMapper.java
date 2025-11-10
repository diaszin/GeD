package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.ProjectCreateDTO;
import com.projetofinal.ged.application.dtos.in.ProjectUpdateDTO;
import com.projetofinal.ged.application.dtos.out.ProjectFolderReadDTO;
import com.projetofinal.ged.application.dtos.out.ProjectReadDTO;
import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {FolderMapper.class, FileMapper.class})
public interface ProjectMapper {
    ProjectMapper instance = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "folders", ignore = true)
    Project createDTOToDomain(ProjectCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "folders", ignore = true)
    JPAProjectEntity domainToEntity(Project project);

    List<Project> entitiesToDomain(List<JPAProjectEntity> entities);

    Project entityToDomain(JPAProjectEntity entity);

    @Mapping(source = "owner", target = "user")
    List<ProjectReadDTO> domainToReadDTO(List<Project> domain);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "folders", ignore = true)
    Project updateDTOToDomain(ProjectUpdateDTO dto);


    ProjectFolderReadDTO domainToProjectFolderReadDTO(Project project);


}
