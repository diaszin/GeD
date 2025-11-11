package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;

import java.util.List;
import java.util.UUID;

public interface FolderRepositoryPort {
    void create(JPAFolderEntity folder);

    List<Folder> getAll();

    void delete(Folder folder);

    Folder getById(UUID id);
}
