package com.projetofinal.ged.application.dtos.out;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AllFoldersInProjectDTO {
    String title;
    UUID id;
}
