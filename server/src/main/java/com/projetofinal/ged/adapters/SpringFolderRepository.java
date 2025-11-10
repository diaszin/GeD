package com.projetofinal.ged.adapters;


import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringFolderRepository extends JpaRepository<JPAFolderEntity, UUID> {

}
