package com.projetofinal.ged.adapters;

import com.projetofinal.ged.domain.Project;
import com.projetofinal.ged.infra.entities.JPAProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringProjectRepository extends JpaRepository<JPAProjectEntity, UUID> {
    @Query("select p from JPAProjectEntity p where p.owner.id = ?1")
    List<JPAProjectEntity> findAllByUser(Long id);

    List<JPAProjectEntity> findAllByOrderByCreatedAtDesc();
}
