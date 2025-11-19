package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.FolderCreateDTO;
import com.projetofinal.ged.application.dtos.in.FolderPartialUpdateDTO;
import com.projetofinal.ged.application.dtos.out.AllFoldersInProjectDTO;
import com.projetofinal.ged.application.dtos.out.FolderReadDTO;
import com.projetofinal.ged.application.dtos.out.ProjectFolderReadDTO;
import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
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
public class FolderMapperImpl implements FolderMapper {

    private final FileMapper fileMapper = Mappers.getMapper( FileMapper.class );
    private final UserMapper userMapper = Mappers.getMapper( UserMapper.class );

    @Override
    public JPAFolderEntity domainToEntity(Folder folder) {
        if ( folder == null ) {
            return null;
        }

        JPAFolderEntity jPAFolderEntity = new JPAFolderEntity();

        jPAFolderEntity.setId( folder.getId() );
        jPAFolderEntity.setTitle( folder.getTitle() );
        jPAFolderEntity.setCreatedAt( folder.getCreatedAt() );
        jPAFolderEntity.setCreatedBy( userMapper.toJPAEntity( folder.getCreatedBy() ) );
        jPAFolderEntity.setUpdatedAt( folder.getUpdatedAt() );
        jPAFolderEntity.setFiles( fileListToJPAFileEntityList( folder.getFiles() ) );
        jPAFolderEntity.setProject( projectToJPAProjectEntity( folder.getProject() ) );

        return jPAFolderEntity;
    }

    @Override
    public Folder entityToDomain(JPAFolderEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        Date createdAt = null;
        User createdBy = null;
        Date updatedAt = null;

        id = entity.getId();
        title = entity.getTitle();
        createdAt = entity.getCreatedAt();
        createdBy = userMapper.toDomainUser( entity.getCreatedBy() );
        updatedAt = entity.getUpdatedAt();

        Folder folder = new Folder( id, title, createdAt, createdBy, updatedAt );

        folder.setProject( jPAProjectEntityToProject( entity.getProject() ) );
        folder.setFiles( fileMapper.entityToDomain( entity.getFiles() ) );

        return folder;
    }

    @Override
    public Folder createDTOToDomain(FolderCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String title = null;

        title = dto.getTitle();

        UUID id = null;
        Date createdAt = null;
        User createdBy = null;
        Date updatedAt = null;

        Folder folder = new Folder( id, title, createdAt, createdBy, updatedAt );

        return folder;
    }

    @Override
    public List<Folder> entityToDomain(List<JPAFolderEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Folder> list = new ArrayList<Folder>( entityList.size() );
        for ( JPAFolderEntity jPAFolderEntity : entityList ) {
            list.add( entityToDomain( jPAFolderEntity ) );
        }

        return list;
    }

    @Override
    public List<FolderReadDTO> domainToFolderReadDTO(List<Folder> domain) {
        if ( domain == null ) {
            return null;
        }

        List<FolderReadDTO> list = new ArrayList<FolderReadDTO>( domain.size() );
        for ( Folder folder : domain ) {
            list.add( folderToFolderReadDTO( folder ) );
        }

        return list;
    }

    @Override
    public List<AllFoldersInProjectDTO> domainToAllFoldersInProjectDTO(List<Folder> domain) {
        if ( domain == null ) {
            return null;
        }

        List<AllFoldersInProjectDTO> list = new ArrayList<AllFoldersInProjectDTO>( domain.size() );
        for ( Folder folder : domain ) {
            list.add( folderToAllFoldersInProjectDTO( folder ) );
        }

        return list;
    }

    @Override
    public Folder partialUpdateDTOToFolder(FolderPartialUpdateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String title = null;

        title = dto.getTitle();

        UUID id = null;
        Date createdAt = null;
        User createdBy = null;
        Date updatedAt = null;

        Folder folder = new Folder( id, title, createdAt, createdBy, updatedAt );

        return folder;
    }

    @Override
    public void updateFolder(Folder modifiedFolder, JPAFolderEntity folder) {
        if ( modifiedFolder == null ) {
            return;
        }

        if ( modifiedFolder.getId() != null ) {
            folder.setId( modifiedFolder.getId() );
        }
        if ( modifiedFolder.getTitle() != null ) {
            folder.setTitle( modifiedFolder.getTitle() );
        }
        if ( modifiedFolder.getCreatedAt() != null ) {
            folder.setCreatedAt( modifiedFolder.getCreatedAt() );
        }
        if ( modifiedFolder.getCreatedBy() != null ) {
            folder.setCreatedBy( userMapper.toJPAEntity( modifiedFolder.getCreatedBy() ) );
        }
        if ( modifiedFolder.getUpdatedAt() != null ) {
            folder.setUpdatedAt( modifiedFolder.getUpdatedAt() );
        }
        if ( folder.getFiles() != null ) {
            List<JPAFileEntity> list = fileListToJPAFileEntityList( modifiedFolder.getFiles() );
            if ( list != null ) {
                folder.getFiles().clear();
                folder.getFiles().addAll( list );
            }
        }
        else {
            List<JPAFileEntity> list = fileListToJPAFileEntityList( modifiedFolder.getFiles() );
            if ( list != null ) {
                folder.setFiles( list );
            }
        }
        if ( modifiedFolder.getProject() != null ) {
            if ( folder.getProject() == null ) {
                folder.setProject( new JPAProjectEntity() );
            }
            projectToJPAProjectEntity1( modifiedFolder.getProject(), folder.getProject() );
        }
    }

    protected List<JPAFileEntity> fileListToJPAFileEntityList(List<File> list) {
        if ( list == null ) {
            return null;
        }

        List<JPAFileEntity> list1 = new ArrayList<JPAFileEntity>( list.size() );
        for ( File file : list ) {
            list1.add( fileMapper.domainToEntity( file ) );
        }

        return list1;
    }

    protected List<JPAFolderEntity> folderListToJPAFolderEntityList(List<Folder> list) {
        if ( list == null ) {
            return null;
        }

        List<JPAFolderEntity> list1 = new ArrayList<JPAFolderEntity>( list.size() );
        for ( Folder folder : list ) {
            list1.add( domainToEntity( folder ) );
        }

        return list1;
    }

    protected JPAProjectEntity projectToJPAProjectEntity(Project project) {
        if ( project == null ) {
            return null;
        }

        JPAProjectEntity jPAProjectEntity = new JPAProjectEntity();

        jPAProjectEntity.id = project.id;
        jPAProjectEntity.title = project.title;
        jPAProjectEntity.owner = userMapper.toJPAEntity( project.owner );
        jPAProjectEntity.createdAt = project.createdAt;
        jPAProjectEntity.folders = folderListToJPAFolderEntityList( project.folders );

        return jPAProjectEntity;
    }

    protected Project jPAProjectEntityToProject(JPAProjectEntity jPAProjectEntity) {
        if ( jPAProjectEntity == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        User owner = null;
        Date createdAt = null;

        id = jPAProjectEntity.id;
        title = jPAProjectEntity.title;
        owner = userMapper.toDomainUser( jPAProjectEntity.owner );
        createdAt = jPAProjectEntity.createdAt;

        Project project = new Project( id, title, owner, createdAt );

        project.folders = entityToDomain( jPAProjectEntity.folders );

        return project;
    }

    protected ProjectFolderReadDTO projectToProjectFolderReadDTO(Project project) {
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

    protected FolderReadDTO folderToFolderReadDTO(Folder folder) {
        if ( folder == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        ProjectFolderReadDTO project = null;

        id = folder.getId();
        title = folder.getTitle();
        project = projectToProjectFolderReadDTO( folder.getProject() );

        FolderReadDTO folderReadDTO = new FolderReadDTO( id, title, project );

        return folderReadDTO;
    }

    protected AllFoldersInProjectDTO folderToAllFoldersInProjectDTO(Folder folder) {
        if ( folder == null ) {
            return null;
        }

        AllFoldersInProjectDTO allFoldersInProjectDTO = new AllFoldersInProjectDTO();

        allFoldersInProjectDTO.setTitle( folder.getTitle() );
        allFoldersInProjectDTO.setId( folder.getId() );

        return allFoldersInProjectDTO;
    }

    protected void projectToJPAProjectEntity1(Project project, JPAProjectEntity mappingTarget) {
        if ( project == null ) {
            return;
        }

        if ( project.id != null ) {
            mappingTarget.id = project.id;
        }
        if ( project.title != null ) {
            mappingTarget.title = project.title;
        }
        if ( project.owner != null ) {
            mappingTarget.owner = userMapper.toJPAEntity( project.owner );
        }
        if ( project.createdAt != null ) {
            mappingTarget.createdAt = project.createdAt;
        }
        if ( mappingTarget.folders != null ) {
            List<JPAFolderEntity> list = folderListToJPAFolderEntityList( project.folders );
            if ( list != null ) {
                mappingTarget.folders.clear();
                mappingTarget.folders.addAll( list );
            }
        }
        else {
            List<JPAFolderEntity> list = folderListToJPAFolderEntityList( project.folders );
            if ( list != null ) {
                mappingTarget.folders = list;
            }
        }
    }
}
