package com.projetofinal.ged.application.dtos.out;

import com.projetofinal.ged.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class FolderReadDTO {
    UUID id;
    String title;
    ProjectFolderReadDTO project;
}
