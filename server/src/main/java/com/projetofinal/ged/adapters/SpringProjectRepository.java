package com.projetofinal.ged.adapters;

import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringProjectRepository extends JpaRepository<JPAProjectEntity, UUID> {
}
