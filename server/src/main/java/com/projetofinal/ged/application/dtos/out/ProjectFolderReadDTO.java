package com.projetofinal.ged.application.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ProjectFolderReadDTO {
    public String title;
    public String id;
    public Date createdAt;
}
