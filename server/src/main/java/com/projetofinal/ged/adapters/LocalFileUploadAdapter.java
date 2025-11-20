package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.UploadedFile;
import com.projetofinal.ged.infra.exceptions.FileNotCreated;
import com.projetofinal.ged.infra.exceptions.FileNotRemoved;
import com.projetofinal.ged.infra.exceptions.NotSupportedExtensionFile;
import com.projetofinal.ged.ports.FileUploadPort;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class LocalFileUploadAdapter implements FileUploadPort {
    private static final String localFolderPath = "upload";


    @Override
    public UploadedFile upload(String filename, byte[] fileBytes) {
        try {
            Path uploadFolder = Paths.get(localFolderPath);
            if (Files.notExists(uploadFolder)) {
                Files.createDirectory(uploadFolder);
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


            return new UploadedFile(fullPath.getFileName().toString(), randomFilename);

        } catch (IOException e) {
            throw new FileNotCreated();
        }
        catch (NotSupportedExtensionFile e){
            throw new NotSupportedExtensionFile();
        }
    }

    @Override
    public void remove(String filename) {
        Path fullPath = Paths.get(localFolderPath + "/" + filename);


        try{
            Files.delete(fullPath);
        }
        catch (IOException e){
            throw new FileNotRemoved();
        }
    }


    private String validate(String filename) {
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
