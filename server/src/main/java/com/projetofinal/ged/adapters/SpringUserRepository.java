package com.projetofinal.ged.adapters;

import com.projetofinal.ged.infra.entities.JPAUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpringUserRepository extends JpaRepository<JPAUserEntity, Long> {
        public JPAUserEntity findByEmail(String email);
}
