package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.FolderFileKpis;
import com.projetofinal.ged.domain.Folder;

import java.util.List;
import java.util.UUID;

public interface FolderServicePort {
    void create(Folder folder, UUID projectId);

    List<Folder> getAll();

    void delete(UUID id);

    Folder getById(UUID id);

    void update(UUID id, Folder folder);

    List<Folder> getByProject(UUID id);

    List<FolderFileKpis> showFilKpis(UUID id);

    List<FolderFileKpis> showImportsKpisByPeriod(UUID id);
}
