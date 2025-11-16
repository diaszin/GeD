package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.UploadedFile;
import com.projetofinal.ged.infra.exceptions.FileNotCreated;
import com.projetofinal.ged.infra.exceptions.NotSupportedExtensionFile;
import com.projetofinal.ged.ports.FileUploadPort;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class LocalFileUploadAdapter implements FileUploadPort {
    private static final String localFolderPath = "upload";


    @Override
    public UploadedFile upload(String filename, byte[] fileBytes) {
        try {
            Path uploadfolder = Paths.get(localFolderPath);
            if(Files.notExists(uploadfolder)){
                Files.createDirectory(uploadfolder);
            }

            String fileExtension = this.validate(filename);

            String randomFilename = UUID.randomUUID().toString();
            Path fullPath = Paths.get(localFolderPath + "/" + randomFilename + "." + fileExtension);

            Files.createFile(fullPath);

            // Processo de escrita do arquivo
            FileOutputStream writer = new FileOutputStream(fullPath.toFile());
            writer.write(fileBytes);
            writer.write(fileBytes);

            writer.close();


            return new UploadedFile(fullPath.toUri().toString(), randomFilename);

        } catch (IOException e) {
            throw new FileNotCreated();
        }
    }


    public String validate(String filename) {
        List<String> ALLOWED_EXTENSIONS = List.of(new String[]{"pdf", "png", "jpeg", "docx", "xlsx", "md"});

        if (!filename.contains(".")) {
            throw new NotSupportedExtensionFile();
        }

        String fileExtension = filename.split("\\.")[1];
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            throw new NotSupportedExtensionFile();
        }

        return fileExtension;
    }
}
