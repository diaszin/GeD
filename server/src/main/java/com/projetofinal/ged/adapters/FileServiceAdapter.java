package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.infra.entities.JPAFileEntity;
import com.projetofinal.ged.infra.exceptions.FileNotFound;
import com.projetofinal.ged.infra.mappers.FileMapper;
import com.projetofinal.ged.ports.FileRepositoryPort;
import com.projetofinal.ged.ports.FileServicePort;
import com.projetofinal.ged.ports.FolderServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class FileServiceAdapter implements FileServicePort {

    private final FileRepositoryPort fileRepository;
    private final FolderServicePort folderService;
    private final FileMapper mapper = FileMapper.instance;

    @Override
    public void register(File file, UUID folderID) {
        Folder folder = this.folderService.getById(folderID);

        file.setFolder(folder);

        this.fileRepository.create(file);
    }

    @Override
    public void delete(UUID id) {
        File file = this.fileRepository.getById(id);

        if(file == null){
            throw new FileNotFound();
        }

        this.fileRepository.delete(file);
    }

    @Override
    public List<File> getAllFilesByFolder(UUID folderID) {
        return this.fileRepository.getAllByFolder(folderID);
    }

    @Override
    public File getById(UUID id) {
        File file = this.fileRepository.getById(id);

        if(file == null){
            throw new FileNotFound();
        }

        return file;
    }

    @Override
    public void update(UUID id, File modifiedFile) {
        File file = this.fileRepository.getById(id);

        if(file == null){
            throw new FileNotFound();
        }

        this.mapper.modifiedFileToFile(modifiedFile, file);


        this.fileRepository.update(file);

    }
}
