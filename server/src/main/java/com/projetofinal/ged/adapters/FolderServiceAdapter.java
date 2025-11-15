package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import com.projetofinal.ged.infra.exceptions.FolderNotFound;
import com.projetofinal.ged.infra.exceptions.ProjectNotFound;
import com.projetofinal.ged.infra.mappers.FolderMapper;
import com.projetofinal.ged.infra.mappers.ProjectMapper;
import com.projetofinal.ged.infra.mappers.UserMapper;
import com.projetofinal.ged.ports.FolderRepositoryPort;
import com.projetofinal.ged.ports.FolderServicePort;
import com.projetofinal.ged.ports.ProjectServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class FolderServiceAdapter implements FolderServicePort {

    public final FolderRepositoryPort folderRepository;
    public final FolderMapper mapper = FolderMapper.instance;
    public final UserMapper userMapper = UserMapper.instance;
    public final ProjectMapper projectMapper = ProjectMapper.instance;
    public final ProjectServicePort projectService;

    @Override
    public void create(Folder folder, UUID projectId) {
        Project project = this.projectService.getById(projectId);

        if(project ==  null) {
            throw new ProjectNotFound();
        }

        folder.setProject(project);

        JPAProjectEntity projectEntity = projectMapper.domainToEntity(project);
        projectEntity.id = project.id;

        JPAUserEntity userEntity = userMapper.toJPAEntity(folder.getCreatedBy());
        userEntity.id = folder.getCreatedBy().getId();

        JPAFolderEntity entity = mapper.domainToEntity(folder);



        entity.project = projectEntity;
        entity.createdBy = userEntity;


        this.folderRepository.create(entity);
    }

    @Override
    public List<Folder> getAll() {
        return this.folderRepository.getAll();
    }



    @Override
    public void delete(UUID id) {
        Folder folder = this.getById(id);

        this.folderRepository.delete(folder);
    }

    @Override
    public Folder getById(UUID id) {
        JPAFolderEntity entity = this.folderRepository.getById(id);
        if(entity ==  null){
            throw new FolderNotFound();
        }

        return  this.mapper.entityToDomain(entity);
    }

    @Override
    public void update(UUID id, Folder partialFolder) {
        JPAFolderEntity folder = this.folderRepository.getById(id);

        if(folder == null){
            throw new FolderNotFound();
        }

        this.mapper.updateFolder(partialFolder, folder);

        this.folderRepository.update(folder);
    }
}
