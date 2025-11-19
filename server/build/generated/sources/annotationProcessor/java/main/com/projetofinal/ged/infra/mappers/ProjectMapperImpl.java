package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.ProjectCreateDTO;
import com.projetofinal.ged.application.dtos.in.ProjectUpdateDTO;
import com.projetofinal.ged.application.dtos.out.ProjectFolderReadDTO;
import com.projetofinal.ged.application.dtos.out.ProjectReadDTO;
import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T00:09:09-0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 25 (Oracle Corporation)"
)
public class ProjectMapperImpl implements ProjectMapper {

    private final FolderMapper folderMapper = Mappers.getMapper( FolderMapper.class );

    @Override
    public Project createDTOToDomain(ProjectCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String title = null;

        title = dto.title;

        UUID id = null;
        User owner = null;
        Date createdAt = null;

        Project project = new Project( id, title, owner, createdAt );

        return project;
    }

    @Override
    public JPAProjectEntity domainToEntity(Project project) {
        if ( project == null ) {
            return null;
        }

        JPAProjectEntity jPAProjectEntity = new JPAProjectEntity();

        jPAProjectEntity.title = project.title;
        jPAProjectEntity.owner = userToJPAUserEntity( project.owner );

        return jPAProjectEntity;
    }

    @Override
    public List<Project> entitiesToDomain(List<JPAProjectEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Project> list = new ArrayList<Project>( entities.size() );
        for ( JPAProjectEntity jPAProjectEntity : entities ) {
            list.add( entityToDomain( jPAProjectEntity ) );
        }

        return list;
    }

    @Override
    public Project entityToDomain(JPAProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        User owner = null;
        Date createdAt = null;

        id = entity.id;
        title = entity.title;
        owner = jPAUserEntityToUser( entity.owner );
        createdAt = entity.createdAt;

        Project project = new Project( id, title, owner, createdAt );

        project.folders = folderMapper.entityToDomain( entity.folders );

        return project;
    }

    @Override
    public List<ProjectReadDTO> domainToReadDTO(List<Project> domain) {
        if ( domain == null ) {
            return null;
        }

        List<ProjectReadDTO> list = new ArrayList<ProjectReadDTO>( domain.size() );
        for ( Project project : domain ) {
            list.add( projectToProjectReadDTO( project ) );
        }

        return list;
    }

    @Override
    public Project updateDTOToDomain(ProjectUpdateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String title = null;

        title = dto.title;

        User owner = null;
        UUID id = null;
        Date createdAt = null;

        Project project = new Project( id, title, owner, createdAt );

        return project;
    }

    @Override
    public ProjectFolderReadDTO domainToProjectFolderReadDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        String title = null;
        String id = null;
        Date createdAt = null;

        title = project.title;
        if ( project.id != null ) {
            id = project.id.toString();
        }
        createdAt = project.createdAt;

        ProjectFolderReadDTO projectFolderReadDTO = new ProjectFolderReadDTO( title, id, createdAt );

        return projectFolderReadDTO;
    }

    protected JPAUserEntity userToJPAUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        JPAUserEntity jPAUserEntity = new JPAUserEntity();

        jPAUserEntity.setId( user.getId() );
        jPAUserEntity.setFullName( user.getFullName() );
        jPAUserEntity.setEmail( user.getEmail() );
        jPAUserEntity.setPassword( user.getPassword() );
        jPAUserEntity.setBirthdayDate( user.getBirthdayDate() );

        return jPAUserEntity;
    }

    protected User jPAUserEntityToUser(JPAUserEntity jPAUserEntity) {
        if ( jPAUserEntity == null ) {
            return null;
        }

        String fullName = null;
        String email = null;
        String password = null;
        Date birthdayDate = null;
        Long id = null;

        fullName = jPAUserEntity.getFullName();
        email = jPAUserEntity.getEmail();
        password = jPAUserEntity.getPassword();
        birthdayDate = jPAUserEntity.getBirthdayDate();
        id = jPAUserEntity.getId();

        User user = new User( id, fullName, email, password, birthdayDate );

        return user;
    }

    protected ProjectReadDTO projectToProjectReadDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        String title = null;
        String id = null;
        Date createdAt = null;
        User owner = null;

        title = project.title;
        if ( project.id != null ) {
            id = project.id.toString();
        }
        createdAt = project.createdAt;
        owner = project.owner;

        ProjectReadDTO projectReadDTO = new ProjectReadDTO( title, id, createdAt, owner );

        return projectReadDTO;
    }
}
