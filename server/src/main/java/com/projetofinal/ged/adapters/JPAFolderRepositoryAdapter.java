package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import com.projetofinal.ged.infra.mappers.FolderMapper;
import com.projetofinal.ged.ports.FolderRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

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

    @Override
    public void delete(Folder folder) {
        JPAFolderEntity entity = this.folderMapper.domainToEntity(folder);
        this.repository.delete(entity);
    }

    @Override
    public JPAFolderEntity getById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public void update(JPAFolderEntity entity) {
        this.repository.saveAndFlush(entity);
    }

    @Override
    public List<JPAFolderEntity> getByProject(UUID id) {
        return this.repository.findAllByProjectId(id);
    }
}
