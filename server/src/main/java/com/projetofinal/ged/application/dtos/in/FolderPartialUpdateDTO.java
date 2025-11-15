package com.projetofinal.ged.application.dtos.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FolderPartialUpdateDTO {
    @NotNull
    @NotEmpty
    String title;
}
