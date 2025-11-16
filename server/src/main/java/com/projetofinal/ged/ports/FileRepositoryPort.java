package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.File;

import java.util.List;
import java.util.UUID;

public interface FileRepositoryPort {
    void create(File file);

    File getById(UUID id);

    void delete(File file);

    List<File> getAllByFolder(UUID folderID);

    void update(File file);
}
