package com.projetofinal.ged.domain;

import java.util.UUID;

public class File {
    UUID id;
    String title;
    Folder folder;
    User createdBy;
    String filePath;
    int version;

    public File(UUID id, String title, Folder folder, User createdBy, String filePath, int version) {
        this.id = id;
        this.title = title;
        this.folder = folder;
        this.createdBy = createdBy;
        this.filePath = filePath;
        this.version = version;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
