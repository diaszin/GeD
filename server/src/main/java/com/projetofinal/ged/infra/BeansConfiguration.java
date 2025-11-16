package com.projetofinal.ged.infra;

import com.projetofinal.ged.adapters.*;
import com.projetofinal.ged.ports.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    UserRepositoryPort userRepositoryPort(){
        return new JPAUserRepositoryAdapter();
    }

    @Bean
    PasswordCriptographyPort passwordCriptographyPort(){
        return new BcryptPasswordCriptographyAdapter();
    }

    @Bean
    AuthServicePort authServicePort(){
        return new JWTAuthServiceAdapter();
    }

    @Bean
    UserServicePort userServicePort(UserRepositoryPort repositoryPort, PasswordCriptographyPort passwordCriptographyPort, AuthServicePort authServicePort){
        return new UserServiceAdapter(repositoryPort,passwordCriptographyPort, authServicePort);
    }

    @Bean
    AuthCurrentUserPort authCurrentUserPort(UserServicePort userServicePort){
        return new JWTAuthCurrentUserAdapter(userServicePort);
    }

    @Bean
    ProjectRepositoryPort projectRepositoryPort(){
        return new JPAProjectRepositoryAdapter();
    }

    @Bean
    ProjectServicePort projectServicePort(ProjectRepositoryPort projectRepositoryPort){
        return new ProjectServiceAdapter(projectRepositoryPort);
    }

    @Bean
    FolderRepositoryPort folderRepositoryPort(){
        return new JPAFolderRepositoryAdapter();
    }

    @Bean
    FolderServicePort folderServicePort(FolderRepositoryPort folderRepositoryPort, ProjectServicePort projectServicePort){
        return new FolderServiceAdapter(folderRepositoryPort, projectServicePort);
    }

    @Bean
    FileUploadPort fileUploadsServicePort(){
        return new LocalFileUploadAdapter();
    }


    @Bean
    FileRepositoryPort fileRepositoryPort(){
        return new JPAFileRepositoryAdapter();
    }

    @Bean
    FileServicePort fileServicePort(FileRepositoryPort repositoryPort, FolderServicePort folderServicePort){
        return new FileServiceAdapter(repositoryPort, folderServicePort);
    }
}
