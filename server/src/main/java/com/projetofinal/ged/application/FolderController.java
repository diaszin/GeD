package com.projetofinal.ged.application;



import com.projetofinal.ged.application.dtos.in.FolderCreateDTO;
import com.projetofinal.ged.application.dtos.in.FolderPartialUpdateDTO;
import com.projetofinal.ged.application.dtos.out.FolderReadDTO;
import com.projetofinal.ged.domain.Folder;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.mappers.FolderMapper;
import com.projetofinal.ged.ports.AuthCurrentUserPort;
import com.projetofinal.ged.ports.FolderServicePort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/folder")
@AllArgsConstructor
public class FolderController {
    private final FolderMapper mapper = FolderMapper.instance;
    private final FolderServicePort folderService;
    private final AuthCurrentUserPort authCurrentUserPort;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid FolderCreateDTO createDTO, @RequestParam("project") @NotNull UUID projectId){
        Folder folder = this.mapper.createDTOToDomain(createDTO);
        IO.println(folder.getTitle());
        User currentUser = authCurrentUserPort.getCurrentUser();

        folder.setCreatedBy(currentUser);

        this.folderService.create(folder, projectId);
    }

    @GetMapping
    public List<FolderReadDTO> getAll(){
        List<Folder> folderList = this.folderService.getAll();
        return this.mapper.domainToFolderReadDTO(folderList);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("id") @Valid @NotNull UUID id){
        this.folderService.delete(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void partialUpdate(@RequestParam("id") @Valid @NotNull UUID id, @RequestBody @Valid FolderPartialUpdateDTO dto){
        Folder folder = this.mapper.partialUpdateDTOToFolder(dto);

        this.folderService.update(id, folder);
    }
}
