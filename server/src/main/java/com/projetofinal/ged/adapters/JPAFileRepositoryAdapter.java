package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import com.projetofinal.ged.infra.mappers.FileMapper;
import com.projetofinal.ged.ports.FileRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class JPAFileRepositoryAdapter implements FileRepositoryPort {
    @Autowired
    SpringFileRepository repository;
    private final FileMapper mapper = FileMapper.instance;

    @Override
    public void create(File file) {
        JPAFileEntity entity = mapper.domainToEntity(file);

        JPAFileEntity recentSavedFile = this.repository.save(entity);

        IO.println(recentSavedFile.title);
    }

    @Override
    public File getById(UUID id) {
        JPAFileEntity entity = this.repository.findById(id).orElse(null);

        return this.mapper.entityToDomain(entity);
    }

    @Override
    public void delete(File file) {
        JPAFileEntity entity = this.mapper.domainToEntity(file);
        this.repository.delete(entity);
    }

    @Override
    public List<File> getAllByFolder(UUID folderID) {
        List<JPAFileEntity> entities = this.repository.findAllByFolderId(folderID);

        return this.mapper.entityToDomain(entities);
    }

    @Override
    public void update(File file) {
        JPAFileEntity entity = this.mapper.domainToEntity(file);

        this.repository.save(entity);
    }
}
