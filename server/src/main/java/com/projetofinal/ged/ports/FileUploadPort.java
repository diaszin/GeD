package com.projetofinal.ged.ports;

import com.projetofinal.ged.domain.UploadedFile;

public interface FileUploadPort {
    UploadedFile upload(String filename, byte[] fileBytes);

    void remove(String filename);
}
