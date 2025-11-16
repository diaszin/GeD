package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.File;

import java.util.List;
import java.util.UUID;

public interface FileServicePort {
    void register(File file, UUID folderID);

    void delete(UUID id);

    List<File> getAllFilesByFolder(UUID folderID);

    File getById(UUID id);

    void update(UUID id, File file);
}
