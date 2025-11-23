package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.UploadedFile;
import org.springframework.core.io.ByteArrayResource;

public interface FileUploadPort {
    UploadedFile upload(String filename, byte[] fileBytes);

    void remove(String filename);

    ByteArrayResource view(String filename);
}
