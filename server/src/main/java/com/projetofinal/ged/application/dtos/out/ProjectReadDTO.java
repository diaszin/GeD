package com.projetofinal.ged.application.dtos.out;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ProjectReadDTO {
    public String title;
    public String id;
    public Date createdAt;
    public UserReadDTO owner;

    public ProjectReadDTO(String title, String id, Date createdAt, User owner) {
        this.title = title;
        this.id = id;
        this.createdAt = createdAt;
        this.owner = UserMapper.instance.domainToReadDTO(owner);
    }
}
