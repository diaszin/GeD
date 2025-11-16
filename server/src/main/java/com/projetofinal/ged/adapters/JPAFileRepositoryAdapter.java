package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import com.projetofinal.ged.infra.mappers.FileMapper;
import com.projetofinal.ged.ports.FileRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
