package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepositoryPort {
    public void create(JPAProjectEntity entity);
    List<Project> getAll();
    List<Project> getAllByUser(Long id);
    void delete(JPAProjectEntity project);
    Project findById(UUID id);
}
