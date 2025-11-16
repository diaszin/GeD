package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.File;

import java.util.UUID;

public interface FileRepositoryPort {
    void create(File file);

    File getById(UUID id);

    void delete(File file);
}
