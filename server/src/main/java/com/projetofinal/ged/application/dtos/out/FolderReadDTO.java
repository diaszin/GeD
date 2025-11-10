package com.projetofinal.ged.application.dtos.out;

import com.projetofinal.ged.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FolderReadDTO {
    String title;
    ProjectFolderReadDTO project;
}
