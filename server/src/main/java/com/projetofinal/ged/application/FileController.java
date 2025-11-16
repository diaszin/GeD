package com.projetofinal.ged.application;

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

@RestController
@RequestMapping("file")
@AllArgsConstructor
public class FileController {

    private final FileUploadPort uploadService;
    private final FileServicePort fileService;
    private final FileMapper mapper = FileMapper.instance;
    private final AuthCurrentUserPort authCurrentUserPort;

    @PostMapping
    public void upload(@ModelAttribute UploadFileDTO fileDTO){
        if(fileDTO.getFile().isEmpty()){
            throw new FileIsEmpty();
        }

        try{
            byte[] fileByte = fileDTO.getFile().getBytes();
            String filename = fileDTO.getFile().getOriginalFilename();

            UploadedFile uploadedFile = uploadService.upload(filename, fileByte);

            IO.println(uploadedFile.path);

            File file = mapper.uploadFileDomainToFileDomain(uploadedFile);

            User currentUser = this.authCurrentUserPort.getCurrentUser();
            file.setCreatedBy(currentUser);
            file.setTitle(fileDTO.getTitle());


            this.fileService.register(file, fileDTO.getFolder());
        }
        catch (IOException e){
            throw new FileNotCreated();
        }
        catch (NotSupportedExtensionFile e){
            throw new NotSupportedExtensionFile();
        }
    }
}
