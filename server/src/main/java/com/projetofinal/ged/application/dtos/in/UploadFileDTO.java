package com.projetofinal.ged.application.dtos.in;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
public class UploadFileDTO {
    MultipartFile file;
    String title;
    UUID folder;
}
