package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;

import java.util.List;

public interface FolderRepositoryPort {
    void create(JPAFolderEntity folder);

    List<Folder> getAll();
}
