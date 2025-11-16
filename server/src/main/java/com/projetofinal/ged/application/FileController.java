package com.projetofinal.ged.application;

import com.projetofinal.ged.application.dtos.in.UploadFileDTO;
import com.projetofinal.ged.infra.exceptions.FileIsEmpty;
import com.projetofinal.ged.infra.exceptions.FileNotCreated;
import com.projetofinal.ged.ports.FileUploadPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("file")
@AllArgsConstructor
public class FileController {

    private final FileUploadPort fileUploadService;

    @PostMapping
    public void upload(@ModelAttribute UploadFileDTO fileDTO){
        if(fileDTO.getFile().isEmpty()){
            throw new FileIsEmpty();
        }

        try{
            byte[] fileByte = fileDTO.getFile().getBytes();
            String filename = fileDTO.getFile().getOriginalFilename();

            fileUploadService.upload(filename, fileByte);
        }
        catch (IOException e){
            throw new FileNotCreated();
        }
    }
}
