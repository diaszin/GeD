package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import com.projetofinal.ged.infra.mappers.ProjectMapper;
import com.projetofinal.ged.ports.ProjectRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class JPAProjectRepositoryAdapter implements ProjectRepositoryPort {
    @Autowired
    private SpringProjectRepository repository;

    private final ProjectMapper mapper = ProjectMapper.instance;


    @Override
    public void create(JPAProjectEntity entity) {

        this.repository.save(entity);
    }

    @Override
    public List<Project> getAll() {
        return mapper.entitiesToDomain(this.repository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    public List<Project> getAllByUser(Long id) {
        List<JPAProjectEntity> allProjects = this.repository.findAllByUser(id);


        return this.mapper.entitiesToDomain(allProjects);
    }

    @Override
    public void delete(JPAProjectEntity project) {
        this.repository.delete(project);
    }

    @Override
    public Project findById(UUID id) {
        return mapper.entityToDomain(this.repository.findById(id).orElse(null));
    }

    @Override
    public void update(JPAProjectEntity entity) {
        this.repository.save(entity);
    }

    @Override
    public Project getById(UUID id) {
        Project project = this.mapper.entityToDomain(this.repository.findById(id).orElse(null));

        return project;
    }
}
