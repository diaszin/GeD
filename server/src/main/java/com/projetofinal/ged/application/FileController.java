package com.projetofinal.ged.application;

import com.projetofinal.ged.application.dtos.in.FilePartialUpdateDTO;
import com.projetofinal.ged.application.dtos.in.FilesInFolderReadDTO;
import com.projetofinal.ged.application.dtos.in.UploadFileDTO;
import com.projetofinal.ged.domain.File;
import com.projetofinal.ged.domain.UploadedFile;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.exceptions.FileIsEmpty;
import com.projetofinal.ged.infra.exceptions.FileNotCreated;
import com.projetofinal.ged.infra.exceptions.NotSupportedExtensionFile;
import com.projetofinal.ged.infra.mappers.FileMapper;
import com.projetofinal.ged.ports.AuthCurrentUserPort;
import com.projetofinal.ged.ports.FileServicePort;
import com.projetofinal.ged.ports.FileUploadPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("file")
@AllArgsConstructor
public class FileController {

    private final FileUploadPort uploadService;
    private final FileServicePort fileService;
    private final FileMapper mapper = FileMapper.instance;
    private final AuthCurrentUserPort authCurrentUserPort;

    @PostMapping
    public void upload(@ModelAttribute UploadFileDTO fileDTO) {
        if (fileDTO.getFile().isEmpty()) {
            throw new FileIsEmpty();
        }

        try {
            byte[] fileByte = fileDTO.getFile().getBytes();
            String filename = fileDTO.getFile().getOriginalFilename();

            UploadedFile uploadedFile = uploadService.upload(filename, fileByte);



            File file = mapper.uploadFileDomainToFileDomain(uploadedFile);

            User currentUser = this.authCurrentUserPort.getCurrentUser();
            file.setCreatedBy(currentUser);
            file.setTitle(fileDTO.getTitle());


            this.fileService.register(file, fileDTO.getFolder());
        } catch (IOException e) {
            throw new FileNotCreated();
        } catch (NotSupportedExtensionFile e) {
            throw new NotSupportedExtensionFile();
        }
    }

    @DeleteMapping
    public void delete(@RequestParam("id") UUID id) {
        File file = this.fileService.getById(id);
        this.fileService.delete(id);



        this.uploadService.remove(file.getFilePath());
    }

    @GetMapping
    public List<FilesInFolderReadDTO> getAllByFolder(@RequestParam("folder") UUID folder) {
        List<File> files = this.fileService.getAllFilesByFolder(folder);



        return this.mapper.entityToFileinFolderReadDTO(files);
    }

    @PatchMapping
    public void partialUpdate(@RequestParam("id") UUID id, @RequestBody FilePartialUpdateDTO dto){
        File file = this.mapper.partialUpdateDTOToDomain(dto);
        this.fileService.update(id, file);

    }


}
