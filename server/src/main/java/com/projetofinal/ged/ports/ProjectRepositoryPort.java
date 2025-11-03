package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;

import java.util.List;

public interface ProjectRepositoryPort {
    public void create(JPAProjectEntity entity);
    List<Project> getAll();
}
