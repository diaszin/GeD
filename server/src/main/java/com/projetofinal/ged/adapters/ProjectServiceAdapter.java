package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import com.projetofinal.ged.infra.exceptions.ProjectNotFound;
import com.projetofinal.ged.infra.exceptions.UserNotAllowedToDeleteProject;
import com.projetofinal.ged.infra.mappers.ProjectMapper;
import com.projetofinal.ged.infra.mappers.UserMapper;
import com.projetofinal.ged.ports.ProjectRepositoryPort;
import com.projetofinal.ged.ports.ProjectServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProjectServiceAdapter implements ProjectServicePort {

    public final ProjectRepositoryPort projectRepository;
    private final ProjectMapper mapper = ProjectMapper.instance;
    private final UserMapper userMapper = UserMapper.instance;

    @Override
    public void create(Project project) {
        JPAProjectEntity jpaProjectEntity = mapper.domainToEntity(project);
        JPAUserEntity owner = userMapper.toJPAEntity(project.owner);

        owner.id = project.owner.getId();

        jpaProjectEntity.owner = owner;

        this.projectRepository.create(jpaProjectEntity);
    }

    @Override
    public List<Project> getAll() {
        return this.projectRepository.getAll();
    }

    @Override
    public List<Project> getAllByUser(User user) {
        return this.projectRepository.getAllByUser(user.getId());
    }

    @Override
    public void delete(UUID id, Long userId) {
        Project project = this.projectRepository.findById(id);

        if(project == null){
            throw new ProjectNotFound();
        }

        if(!project.owner.getId().equals(userId)){
            throw new UserNotAllowedToDeleteProject();
        }

        JPAProjectEntity entity = mapper.domainToEntity(project);
        entity.id = project.id;
        this.projectRepository.delete(entity);
    }


}
