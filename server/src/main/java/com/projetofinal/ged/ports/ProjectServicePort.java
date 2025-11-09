package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.domain.User;

import java.util.List;
import java.util.UUID;

public interface ProjectServicePort {
    void create(Project project);

    List<Project> getAll();

    List<Project> getAllByUser(User user);

    void delete(UUID id, Long ownerId);
    void update(UUID id, Project newProject);
}
