package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import com.projetofinal.ged.infra.mappers.ProjectMapper;
import com.projetofinal.ged.infra.mappers.UserMapper;
import com.projetofinal.ged.ports.ProjectRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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
        return mapper.entitiesToDomain(this.repository.findAll());
    }

    @Override
    public List<Project> getAllByUser(Long id) {
        List<JPAProjectEntity> allProjects = this.repository.findAllByUser(id);


        return this.mapper.entitiesToDomain(allProjects);
    }
}
