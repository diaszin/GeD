package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.File;

import java.util.UUID;

public interface FileServicePort {
    void register(File file, UUID folderID);

    void delete(UUID id);
}
