package com.projetofinal.ged.infra.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "projetos")
@DynamicUpdate
public class JPAProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    public UUID id;

    @Column(name = "titulo")
    public String title;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public JPAUserEntity owner;

    @CreationTimestamp
    @Column(name = "data_de_criacao")
    public Date createdAt;
}
