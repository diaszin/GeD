package com.projetofinal.ged.application.dtos.in;

import com.projetofinal.ged.application.dtos.out.UserReadDTO;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class FilesInFolderReadDTO {
    String id;
    String title;
    String extension;
    UserReadDTO createdBy;
    String uploadDate;

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        if (uploadDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");


            this.uploadDate = dateFormat.format(uploadDate) + " as " + hourFormat.format(uploadDate);
        } else {
            this.uploadDate = null;
        }
    }
}
