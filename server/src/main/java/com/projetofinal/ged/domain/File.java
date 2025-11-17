package com.projetofinal.ged.domain;

import java.util.Date;
import java.util.UUID;

public class File {
    UUID id;
    String title;
    Folder folder;
    User createdBy;
    String filePath;
    String extension;
    Date uploadDate;


    public File(UUID id, String title, Folder folder, User createdBy, String filePath, Date uploadDate) {
        this.id = id;
        this.title = title;
        this.folder = folder;
        this.createdBy = createdBy;
        this.filePath = filePath;

        if (filePath.contains(".")) {
            this.extension = filePath.split("\\.")[1];
        }

        this.uploadDate = uploadDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        if (filePath.contains(".")) {
            this.extension = filePath.split("\\.")[1];
        }
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
