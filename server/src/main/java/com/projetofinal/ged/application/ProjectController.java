package com.projetofinal.ged.application;

import com.projetofinal.ged.application.dtos.in.ProjectCreateDTO;
import com.projetofinal.ged.application.dtos.in.ProjectUpdateDTO;
import com.projetofinal.ged.application.dtos.out.ProjectReadDTO;
import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.mappers.ProjectMapper;
import com.projetofinal.ged.ports.AuthCurrentUserPort;
import com.projetofinal.ged.ports.ProjectServicePort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
    private final AuthCurrentUserPort authCurrentUserPort;
    private final ProjectServicePort projectServicePort;
    private final ProjectMapper mapper = ProjectMapper.instance;

    @GetMapping
    public List<ProjectReadDTO> getAllProject(){
        User user = authCurrentUserPort.getCurrentUser();
        return this.mapper.domainToReadDTO(this.projectServicePort.getAllByUser(user));
    }

    @PostMapping
    public void create(@RequestBody() ProjectCreateDTO dto){
        Project project = mapper.createDTOToDomain(dto);

        project.owner = authCurrentUserPort.getCurrentUser();

        this.projectServicePort.create(project);
    }

    @DeleteMapping
    public void delete(@RequestParam UUID id){
        User currentUser = authCurrentUserPort.getCurrentUser();
        this.projectServicePort.delete(id, currentUser.getId());
    }

    @PutMapping
    public void update(@RequestBody ProjectUpdateDTO updateDTO, @RequestParam UUID id){
        Project updateProject = this.mapper.updateDTOToDomain(updateDTO);
        this.projectServicePort.update(id, updateProject);
    }
}
