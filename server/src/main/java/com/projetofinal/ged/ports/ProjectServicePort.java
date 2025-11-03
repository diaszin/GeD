package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.Project;

import java.util.List;

public interface ProjectServicePort {
    void create(Project project);

    List<Project> getAll();
}
