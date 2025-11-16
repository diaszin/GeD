package com.projetofinal.ged.application.dtos.in;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadFileDTO {
    MultipartFile file;
}
