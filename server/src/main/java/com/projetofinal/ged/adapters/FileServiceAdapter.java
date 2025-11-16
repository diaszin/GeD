package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import com.projetofinal.ged.ports.FileRepositoryPort;
import com.projetofinal.ged.ports.FileServicePort;
import com.projetofinal.ged.ports.FolderServicePort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class FileServiceAdapter implements FileServicePort {

    private final FileRepositoryPort fileRepository;
    private final FolderServicePort folderService;

    @Override
    public void register(File file, UUID folderID) {
        Folder folder = this.folderService.getById(folderID);

        file.setFolder(folder);

        this.fileRepository.create(file);
    }
}
