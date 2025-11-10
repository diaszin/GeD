package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import com.projetofinal.ged.infra.mappers.FolderMapper;
import com.projetofinal.ged.ports.FolderRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JPAFolderRepositoryAdapter implements FolderRepositoryPort {
    @Autowired
    SpringFolderRepository repository;

    FolderMapper folderMapper = FolderMapper.instance;

    @Override
    public void create(JPAFolderEntity folder) {
        this.repository.save(folder);
    }

    @Override
    public List<Folder> getAll() {
        List<JPAFolderEntity> entity = this.repository.findAll();
        return folderMapper.entityToDomain(entity);
    }
}
