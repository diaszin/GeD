package com.projetofinal.ged.adapters;

import com.projetofinal.ged.entities.JPAUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringUserRepository extends JpaRepository<JPAUserEntity, Long> {

}
