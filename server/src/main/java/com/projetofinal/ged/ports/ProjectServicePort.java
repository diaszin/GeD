package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.domain.User;

import java.util.List;

public interface ProjectServicePort {
    void create(Project project);

    List<Project> getAll();

    List<Project> getAllByUser(User user);
}
