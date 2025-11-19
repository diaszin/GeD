package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.FilePartialUpdateDTO;
import com.projetofinal.ged.application.dtos.in.FilesInFolderReadDTO;
import com.projetofinal.ged.application.dtos.out.UserReadDTO;
import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.domain.UploadedFile;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T00:09:09-0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 25 (Oracle Corporation)"
)
public class FileMapperImpl implements FileMapper {

    @Override
    public JPAFileEntity domainToEntity(File file) {
        if ( file == null ) {
            return null;
        }

        JPAFileEntity jPAFileEntity = new JPAFileEntity();

        jPAFileEntity.user = userToJPAUserEntity( file.getCreatedBy() );
        jPAFileEntity.generatedFilename = file.getFilePath();
        jPAFileEntity.id = file.getId();
        jPAFileEntity.title = file.getTitle();
        jPAFileEntity.folder = folderToJPAFolderEntity( file.getFolder() );
        jPAFileEntity.uploadDate = file.getUploadDate();

        return jPAFileEntity;
    }

    @Override
    public File entityToDomain(JPAFileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User createdBy = null;
        String filePath = null;
        UUID id = null;
        String title = null;
        Folder folder = null;
        Date uploadDate = null;

        createdBy = jPAUserEntityToUser( entity.user );
        filePath = entity.generatedFilename;
        id = entity.id;
        title = entity.title;
        folder = jPAFolderEntityToFolder( entity.folder );
        uploadDate = entity.uploadDate;

        File file = new File( id, title, folder, createdBy, filePath, uploadDate );

        return file;
    }

    @Override
    public List<File> entityToDomain(List<JPAFileEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<File> list = new ArrayList<File>( entity.size() );
        for ( JPAFileEntity jPAFileEntity : entity ) {
            list.add( entityToDomain( jPAFileEntity ) );
        }

        return list;
    }

    @Override
    public File uploadFileDomainToFileDomain(UploadedFile uploadedFile) {
        if ( uploadedFile == null ) {
            return null;
        }

        String filePath = null;

        filePath = uploadedFile.path;

        UUID id = null;
        String title = null;
        Folder folder = null;
        User createdBy = null;
        Date uploadDate = null;

        File file = new File( id, title, folder, createdBy, filePath, uploadDate );

        return file;
    }

    @Override
    public List<FilesInFolderReadDTO> entityToFileinFolderReadDTO(List<File> files) {
        if ( files == null ) {
            return null;
        }

        List<FilesInFolderReadDTO> list = new ArrayList<FilesInFolderReadDTO>( files.size() );
        for ( File file : files ) {
            list.add( fileToFilesInFolderReadDTO( file ) );
        }

        return list;
    }

    @Override
    public File partialUpdateDTOToDomain(FilePartialUpdateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String title = null;

        title = dto.getTitle();

        UUID id = null;
        Folder folder = null;
        User createdBy = null;
        String filePath = null;
        Date uploadDate = null;

        File file = new File( id, title, folder, createdBy, filePath, uploadDate );

        return file;
    }

    @Override
    public void modifiedFileToFile(File modifiedFile, File file) {
        if ( modifiedFile == null ) {
            return;
        }

        if ( modifiedFile.getId() != null ) {
            file.setId( modifiedFile.getId() );
        }
        if ( modifiedFile.getTitle() != null ) {
            file.setTitle( modifiedFile.getTitle() );
        }
        if ( modifiedFile.getFolder() != null ) {
            file.setFolder( modifiedFile.getFolder() );
        }
        if ( modifiedFile.getCreatedBy() != null ) {
            file.setCreatedBy( modifiedFile.getCreatedBy() );
        }
        if ( modifiedFile.getFilePath() != null ) {
            file.setFilePath( modifiedFile.getFilePath() );
        }
        if ( modifiedFile.getExtension() != null ) {
            file.setExtension( modifiedFile.getExtension() );
        }
        if ( modifiedFile.getUploadDate() != null ) {
            file.setUploadDate( modifiedFile.getUploadDate() );
        }
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

    protected List<JPAFileEntity> fileListToJPAFileEntityList(List<File> list) {
        if ( list == null ) {
            return null;
        }

        List<JPAFileEntity> list1 = new ArrayList<JPAFileEntity>( list.size() );
        for ( File file : list ) {
            list1.add( domainToEntity( file ) );
        }

        return list1;
    }

    protected List<JPAFolderEntity> folderListToJPAFolderEntityList(List<Folder> list) {
        if ( list == null ) {
            return null;
        }

        List<JPAFolderEntity> list1 = new ArrayList<JPAFolderEntity>( list.size() );
        for ( Folder folder : list ) {
            list1.add( folderToJPAFolderEntity( folder ) );
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
        jPAProjectEntity.owner = userToJPAUserEntity( project.owner );
        jPAProjectEntity.createdAt = project.createdAt;
        jPAProjectEntity.folders = folderListToJPAFolderEntityList( project.folders );

        return jPAProjectEntity;
    }

    protected JPAFolderEntity folderToJPAFolderEntity(Folder folder) {
        if ( folder == null ) {
            return null;
        }

        JPAFolderEntity jPAFolderEntity = new JPAFolderEntity();

        jPAFolderEntity.setId( folder.getId() );
        jPAFolderEntity.setTitle( folder.getTitle() );
        jPAFolderEntity.setCreatedAt( folder.getCreatedAt() );
        jPAFolderEntity.setCreatedBy( userToJPAUserEntity( folder.getCreatedBy() ) );
        jPAFolderEntity.setUpdatedAt( folder.getUpdatedAt() );
        jPAFolderEntity.setFiles( fileListToJPAFileEntityList( folder.getFiles() ) );
        jPAFolderEntity.setProject( projectToJPAProjectEntity( folder.getProject() ) );

        return jPAFolderEntity;
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

    protected List<Folder> jPAFolderEntityListToFolderList(List<JPAFolderEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Folder> list1 = new ArrayList<Folder>( list.size() );
        for ( JPAFolderEntity jPAFolderEntity : list ) {
            list1.add( jPAFolderEntityToFolder( jPAFolderEntity ) );
        }

        return list1;
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
        owner = jPAUserEntityToUser( jPAProjectEntity.owner );
        createdAt = jPAProjectEntity.createdAt;

        Project project = new Project( id, title, owner, createdAt );

        project.folders = jPAFolderEntityListToFolderList( jPAProjectEntity.folders );

        return project;
    }

    protected Folder jPAFolderEntityToFolder(JPAFolderEntity jPAFolderEntity) {
        if ( jPAFolderEntity == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        Date createdAt = null;
        User createdBy = null;
        Date updatedAt = null;

        id = jPAFolderEntity.getId();
        title = jPAFolderEntity.getTitle();
        createdAt = jPAFolderEntity.getCreatedAt();
        createdBy = jPAUserEntityToUser( jPAFolderEntity.getCreatedBy() );
        updatedAt = jPAFolderEntity.getUpdatedAt();

        Folder folder = new Folder( id, title, createdAt, createdBy, updatedAt );

        folder.setProject( jPAProjectEntityToProject( jPAFolderEntity.getProject() ) );
        folder.setFiles( entityToDomain( jPAFolderEntity.getFiles() ) );

        return folder;
    }

    protected UserReadDTO userToUserReadDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;

        email = user.getEmail();

        String name = null;

        UserReadDTO userReadDTO = new UserReadDTO( name, email );

        return userReadDTO;
    }

    protected FilesInFolderReadDTO fileToFilesInFolderReadDTO(File file) {
        if ( file == null ) {
            return null;
        }

        FilesInFolderReadDTO filesInFolderReadDTO = new FilesInFolderReadDTO();

        filesInFolderReadDTO.setUploadDate( file.getUploadDate() );
        if ( file.getId() != null ) {
            filesInFolderReadDTO.setId( file.getId().toString() );
        }
        filesInFolderReadDTO.setTitle( file.getTitle() );
        filesInFolderReadDTO.setExtension( file.getExtension() );
        filesInFolderReadDTO.setCreatedBy( userToUserReadDTO( file.getCreatedBy() ) );

        return filesInFolderReadDTO;
    }
}
