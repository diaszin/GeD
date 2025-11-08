package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.ProjectCreateDTO;
import com.projetofinal.ged.application.dtos.out.ProjectReadDTO;
import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper instance = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Project createDTOToDomain(ProjectCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    JPAProjectEntity domainToEntity(Project project);

    List<Project> entitiesToDomain(List<JPAProjectEntity> entities);

    @Mapping(source = "owner", target = "user")
    List<ProjectReadDTO> domainToReadDTO(List<Project> domain);
}
