package com.projetofinal.ged.domain;

import java.beans.ConstructorProperties;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Project {
    public UUID id;
    public String title;
    public User owner;
    public Date createdAt;
    public List<Folder> folders;


    public Project(UUID id, String title, User owner, Date createdAt) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.createdAt = createdAt;
    }
}
